package com.demo.crawlerapi.manager;

import com.demo.crawlerapi.Entity.DuplicatesEmailRecords;
import com.demo.crawlerapi.Entity.User;
import com.demo.crawlerapi.Repository.DuplicateEamilRecordRepository;
import com.demo.crawlerapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchManager {
    @Autowired
    private JdbcTemplateManager jdbcTemplateManager;

    @Autowired
    private DuplicateEamilRecordRepository duplicateEamilRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public List<String> getAllFixtures(){
        return jdbcTemplateManager.getAllFixtures();
    }

    public List<String> getAllResults() {
        return jdbcTemplateManager.getAllResults();
    }

    public List<String> getResultBySeriesId(String seriesId) {
        return jdbcTemplateManager.getResultsBySeriesId(seriesId);
    }

    public List<String> getResultsByGameId(String gameId){
        return jdbcTemplateManager.getResultsByGameId(gameId);
    }

    public List<User> getUserByEmail(String email){
        List<User> user = userRepository.findByEmail("admin@g00glen00b.be");
        return user;
    }

    public List<DuplicatesEmailRecords> getUserByEmailId(String email){
        List<String> distinctEmails = distinctEmails();
       /* List<String> distinctEmails = new ArrayList<>();
        distinctEmails.add("admin.cgv@admin.net.au");*/
        int duplicatecounter = 0;
        List<Long> chosenFromDuplicateIds = new ArrayList<>();
        for (String emailval:distinctEmails
             ) {
            List<DuplicatesEmailRecords> duplicateEamilRecords= duplicateEamilRecordRepository.findByEmail(emailval);
            HashSet<String> emailHashSet;

            //this hasshet is for first gorup id requirement
          //  emailHashSet = new HashSet<>(duplicateEamilRecords.stream().map(DuplicatesEmailRecords::getFullName_Email_Address).collect(Collectors.toList()));


            Map<String, List<DuplicatesEmailRecords>> duplicatesEmailRecordsMap = new HashMap<>();

            //this hashset is for hard duplicates
            emailHashSet = new HashSet<String>();
            for (DuplicatesEmailRecords duplicatesEmailRecord: duplicateEamilRecords
            ) {



                String hashValueEntry;

                //hashValueEntry = duplicatesEmailRecord.getFullName_Email_Address().toLowerCase() + duplicatesEmailRecord.getPhone().replace("-", "").replaceAll("\\s+","").trim();
                hashValueEntry = duplicatesEmailRecord.getFullName_Email_Address().toLowerCase();
                List<DuplicatesEmailRecords> existingDuplicateRecords = duplicatesEmailRecordsMap.get(hashValueEntry);
                if(existingDuplicateRecords!=null){
                    existingDuplicateRecords.add(duplicatesEmailRecord);
                    //duplicatesEmailRecordsMap.put(hashValueEntry, hashCOunt+1);
                } else {
                    List<DuplicatesEmailRecords> newDuplicateRecords= new ArrayList<>();
                    newDuplicateRecords.add(duplicatesEmailRecord);
                    duplicatesEmailRecordsMap.put(hashValueEntry, newDuplicateRecords);
                }

                emailHashSet.add(hashValueEntry);
            }





            if(duplicateEamilRecords.size() > 1 && emailHashSet.size()==1){
                markDeletionAndSave(chosenFromDuplicateIds, duplicateEamilRecords);
                // duplicateEamilRecordRepository.save(latestRecord);

            } else if(duplicateEamilRecords.size() >1 && emailHashSet.size() >1){//for ones with different phone number

                markForGroupEmail(duplicateEamilRecords);
                //within has if theres same entry, then mark the old one for deletion
                for (String hseValue: emailHashSet
                     ) {
                    if(duplicatesEmailRecordsMap.get(hseValue).size()>1){
                        markDeletionAndSave(chosenFromDuplicateIds, duplicatesEmailRecordsMap.get(hseValue));
                    }
                }

               // markForGroupEmail(duplicateEamilRecords);
            }

            //Below record is for udpating group email for duplicate emails
            /*if(duplicateEamilRecords.size() > 1 && emailHashSet.size()>1){
                for (DuplicatesEmailRecords d: duplicateEamilRecords
                ) {
                    duplicatesEmailRecordsMap.put(d.getId(), d );
                }


                duplicateEamilRecords.sort(Comparator.comparing(DuplicatesEmailRecords::getCreated_date_formatted).reversed());
                DuplicatesEmailRecords latestRecord =  duplicateEamilRecords.get(0);

                duplicateEamilRecords.stream().filter(item-> !item.getId().equals(latestRecord.getId())).forEach(item-> item.setGroup_email(latestRecord.getEmail()));

                duplicateEamilRecordRepository.saveAll(duplicateEamilRecords);
            }*/



           // return duplicateEamilRecordRepositories;
        }

        List<String> chosenIdString = new ArrayList<>();
        for (Long idval: chosenFromDuplicateIds
             ) {
            chosenIdString.add(idval.toString());
        }

        String citiesCommaSeparated = String.join(",", chosenIdString);
        System.out.println(citiesCommaSeparated);

       return null;
    }

    private void markForGroupEmail(List<DuplicatesEmailRecords> duplicateEamilRecords) {
        duplicateEamilRecords.sort(Comparator.comparing(DuplicatesEmailRecords::getCreated_date_formatted).reversed());
        DuplicatesEmailRecords latestRecord =  duplicateEamilRecords.get(0);
        duplicateEamilRecords.stream().filter(item-> !item.getId().equals(latestRecord.getId())).forEach(item-> item.setGroup_email(latestRecord.getEmail()));
        duplicateEamilRecords.stream().forEach(item-> item.setToBeDeleted(false));
        duplicateEamilRecordRepository.saveAll(duplicateEamilRecords);
    }

    private void markDeletionAndSave(List<Long> chosenFromDuplicateIds, List<DuplicatesEmailRecords> duplicateEamilRecords) {
        duplicateEamilRecords.sort(Comparator.comparing(DuplicatesEmailRecords::getCreated_date_formatted).reversed());
        DuplicatesEmailRecords latestRecord =  duplicateEamilRecords.get(0);
        duplicateEamilRecords.stream().filter(item-> !item.getId().equals(latestRecord.getId())).forEach(item-> item.setToBeDeleted(true));
        for (DuplicatesEmailRecords dd: duplicateEamilRecords
             ) {
            if(latestRecord.getOpt_out_from_all_winc().equals(0) && dd.getOpt_out_from_all_winc().equals(1)) latestRecord.setOpt_out_from_all_winc(1);
            if(latestRecord.getSpecial_offers_promotions_winc().equals(0) && dd.getSpecial_offers_promotions_winc().equals(1)) latestRecord.setSpecial_offers_promotions_winc(1);
            if(latestRecord.getSales_phone_call_winc().equals(0) && dd.getSales_phone_call_winc().equals(1)) latestRecord.setSales_phone_call_winc(1);
            if(latestRecord.getTemp_exclude_from_marketing_automa_winc().equals(0) && dd.getTemp_exclude_from_marketing_automa_winc().equals(1)) latestRecord.setTemp_exclude_from_marketing_automa_winc(1);
            if(latestRecord.getCatalogue_release_winc().equals(0) && dd.getCatalogue_release_winc().equals(1)) latestRecord.setCatalogue_release_winc(1);
            if(latestRecord.getEmail_opt_out_winc().equals(0) && dd.getEmail_opt_out_winc().equals(1)) latestRecord.setEmail_opt_out_winc(1);
            if((latestRecord.getKey_contact()==null || latestRecord.getKey_contact().equals(false))) {
                if( (dd.getKey_contact()!=null && dd.getKey_contact().equals(true)) || (dd.getRoles()!=null && dd.getRoles().toLowerCase().contains("primary contact")))
                latestRecord.setKey_contact(true);
            }
        }
        latestRecord.setMaster_copy(true);

        duplicateEamilRecords.remove(0);
        duplicateEamilRecords.add(latestRecord);

        chosenFromDuplicateIds.add(latestRecord.getId());

        duplicateEamilRecordRepository.saveAll(duplicateEamilRecords);
    }

    public List<String> distinctEmails(){
        List<String> distinctEmailss = duplicateEamilRecordRepository.getDiscintctEmails();
        List<String> trimmedDistinctEmails = distinctEmailss.stream().map(String :: trim).collect(Collectors.toList());
        //List<String> distinctEmailss = new ArrayList<>();
      //  distinctEmailss.add("asayedhuq@mcec.com.au");
        return trimmedDistinctEmails;
    }
}
