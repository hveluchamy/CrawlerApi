package com.demo.crawlerapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/CrawlerApi")
@RestController
public class CrawlerApiController {

    @RequestMapping(value = "/crawlerTest", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDFByteStream(HttpServletResponse response) throws IOException {
        //return "test test";
        /*  SettlementItem settlementItem = new SettlementItem();
        settlementItem.setAvgChargeRate("53.0");
        settlementItem.setChargeExclTax("$105.5");
        settlementItem.setDescription("sample testing");
        settlementItem.setChargeInclGst("$115.5");
        settlementItem.setGstVale("$10.5");
        settlementItem.setTotalTransactionValue("$545.04");
        //This is needed to be added to table footer
        settlementItem.setPaymentDeducutedValue("$11.4");



        List<SettlementItem> settlementItemList = new ArrayList<SettlementItem>();
        settlementItemList.add(settlementItem);
        //settlementItemList.add(settlementItem1);

        Settlement settlement = new Settlement();
        settlement.setSettlementItem(settlementItemList);
        settlement.setAbnNumber("ABN23232");
        settlement.setAmountDue("$23423.00");
        settlement.setCurrency("AUD");
        settlement.setInvoiceDate("23/09/18");
        settlement.setInvoiceNumber("INV132131");
        settlement.setPaymentDeducutedValue("2343");


        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerName("Test cust");
        customerDetails.setAddress1("1 Line 1 st");
        customerDetails.setAddress2("second line of address");
        customerDetails.setCountry("Australia");
        customerDetails.setPinCode("3073");
        customerDetails.setState("VIC");

        settlement.setCustomerDetails(customerDetails);

        byte[] bytes = jasperReportManager.generateDocumentAndReturnPDFByteStream("SETTLEMENT", settlement);

        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=\"" + "mypdfresponse" + ".pdf\"")
                .body(bytes);*/

        return null;
    }


    @RequestMapping(value = "/sampleApi", method = RequestMethod.GET)
    public String getSampleApi(HttpServletResponse response) throws IOException {
        return "test test";
    }
}
