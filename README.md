# CrawlerApi

# Setup steps
1. I have attached the rollforward script (CreateTables.sql). Please update spring datasource property file appropriately in applicaiton.properties
2. To setup the data in your database, please run the following API. (http://localhost:8085/CrawlerApi/setupdata). This will add data to database tables created in script file above.
3. The depth to which the crawler can go can be set up in application.properties file property depth.value

# APIs:
1. Get all fixtures (http://localhost:8085/CrawlerApi/fixtures)
2. Get all results (http://localhost:8085/CrawlerApi/results)
3. Get results by game id (http://localhost:8085/CrawlerApi/resultsByGame/{gameId}) - pathvariable
4. Get results by series id (http://localhost:8085/CrawlerApi/resultsBySeries ) -please pass parameter seriesId
