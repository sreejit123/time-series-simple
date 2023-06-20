# time-series-simple
A simple sliding window time series application. 

## Run steps
This application relies on Spring Boot application events and raw websockets to publish them to front-end users.
<br/>
To run this application, type <p/>
``mvn clean springboot:run``
<br/>
This starts the Tomcat server at port 8080.<br/>
You can test it using websocat. To install websocat via Brew on Mac, type in terminal
<br/>
``brew install websocat``
<br />
Once websocat is installed, initiate a session using<br />
``websocat ws://localhost:8080/ws/register``
Once connection is established, type in the prompt<br/>
``{"user": "<your-user-id>", "rule": 1}``
<br/>
Here there are two rules 1 and 2, you can configure a rule of your own in <b>RulePropertiesConfiguration</b>.
<p/>
If things are fine, you will receive a message "OK" on the websocket channel.

We have a REST endpoint to start ingesting data from transactions.json. Once you have subscribed on websocket, 
open another terminal window and type<br />
``curl -X POST localhost:8080/ingestion/start``
This starts the ingestion and you can find alert events on your websocket channel, like<p/>
``{"timestamp":1687271696408,"bucketKey":{"accountNumber":"4444444444","transactionDirection":"ALL","bucketType":"DAY"},"bucketData":{"slidingIndexOnBlock":0,"aggregateData":16468.48},"rule":{"ruleNumber":1,"bucketType":"DAY","transactionDirection":"ALL","amountThreshold":10000.0,"operator":"GT"},"source":{}}``

