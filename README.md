GoEuro Problem Set
==================

The purpose of this test is to see how you approach a problem and what
your solutions look like. The requirements for this test are simple and
should be straightforward to grasp. When implementing a solution, please
keep things as well engineered as you would do for your production code.
We will review your test carefully. This will be an important part of
your application process. That said...

Implement an API query and transform this data into a csv file
--------------------------------------------------------------

Create a java command line tool that takes as an input parameter a string

    java -jar GoEuroTest.jar "STRING"
    
The program takes this string and queries with it our Location JSON API.

The app should use this API endpoint:

    http://api.goeuro.com/api/v2/position/suggest/en/STRING
    
Where STRING is the string that the user has entered as a parameter when
calling the tool.

The API endpoint returns JSON documents like these:

    [  
    {  
        "_id":376217,
        "key":null,
        "name":"Berlin",
        "fullName":"Berlin, Germany",
        "iata_airport_code":null,
        "type":"location",
        "country":"Germany",
        "geo_position":{  
            "latitude":52.52437,
            "longitude":13.41053
        },
        "locationId":8384,
        "inEurope":true,
        "countryCode":"DE",
        "coreCountry":true,
        "distance":null
    },
    {  
        "_id":393496,
        "key":null,
        "name":"Bergen",
        "fullName":"Bergen, Norway",
        "iata_airport_code":null,
        "type":"location",
        "country":"Norway",
        "geo_position":{  
            "latitude":60.39292907714844,
            "longitude":5.324578762054443
        },
        "locationId":25708,
        "inEurope":true,
        "countryCode":"NO",
        "coreCountry":false,
        "distance":null
    }
    ]
       
The endpoint always responds with a JSON array that contains JSON objects
(when working correctly) as elements. Each object, among other things, has
a 'name' and a 'geo_position' key. the 'geo_position' key is an object with
'latitude' and 'longitude' keys, and nothing else.

If no matches are found, an empty JSON array is returned.

The program should query the API with the user input and create a CSV file
from it. The CSV file should contain an entry from each item in the JSON
array with the from: 

    _id,name,type,latitude,longitude
    
Your solution
-------------

Please implement your solution as a stand alone application which can be
started from the command line, i.e. send us a jar file with all dependencies.
You can use Java 7 and open source libraries that you think help you fulfill
this task.

Also send us the source code to your solution.

We will evaluate your code as well as the functionality of the program: Does
it run, how does it handle errors, how well engineered is your solution, etc.
