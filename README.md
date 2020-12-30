# SQL User Defined Function (UDF) for Lenses.io

## Overview

Custom UDF for Lenses.io SQL Engines that detects if coordinates (lat,lon) intercept a defined polygon using PIP (point-in-polygon) algorithm. 

Works for both Snapshot SQL and Streaming SQL Engines.

Syntax of UDF IS:

```sql
SELECT lat,lon,_time, spacialmap(lat,lon, <polygon json>) as is_on_runway FROM coordinates LIMIT 100;
```

See [Geospatial analytics for Apache Kafka](https://lenses.io/blog/2020/12/geo-spatial-sql-data-processing-for-apache-kafka/) blog for more details. 

## Lenses.io Environment

If you don't have Lenses.io setup for your Kafka, use the Lenses.io Box Docker (an all-in-one Kafka & Lenses.io environment). Get the docker run command and free license key from [lenses.io/box](https://lenses.io/box/)

### Ensure you mount the jars for the custom UDF to the /opt/lenses/plugins/udf/ directory. The following Jars:


json-20201115.jar
lensesio-lemastergui-udf-spatialmap-1.2.0.jar
lenses-sql-udf-4.0.0.jar

Run Box whilst mounting the volume with the command:

```bash
docker run -e ADV_HOST=127.0.0.1 -e EULA="<<LICENSE KEY>>" --volume /$MY_BUILD_TARGET_DIR/:/opt/lenses/plugins/udf/  --rm -p 3030:3030 -p 9092:9092 lensesio/box
```

## Sample data to test

1.	In Explore screen, create a topic coordinates
2.	In Explore > coordinates > Action > Change Types > Set the serialisation to String:JSON
3.	In Explore > coordinates > Action > Insert Messages, paste in the following JSON:

```json
{}[
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:47.000+0000",
      "lat": 51.4625134904,
      "lon": -0.466060129506,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:45.000+0000",
      "lat": 51.4625674496,
      "lon": -0.46606014485,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:42.000+0000",
      "lat": 51.4626483884,
      "lon": -0.466060167866,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:36.000+0000",
      "lat": 51.4629002092,
      "lon": -0.465959195709,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:29.000+0000",
      "lat": 51.4630710625,
      "lon": -0.466118027573,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:27.000+0000",
      "lat": 51.4631340085,
      "lon": -0.466175785143,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:24.000+0000",
      "lat": 51.4631789649,
      "lon": -0.466262407513,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:23.000+0000",
      "lat": 51.4631879548,
      "lon": -0.46629127994,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:21.000+0000",
      "lat": 51.4632059315,
      "lon": -0.466377894666,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:20.000+0000",
      "lat": 51.4631969317,
      "lon": -0.466435631715,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:19.000+0000",
      "lat": 51.4631969252,
      "lon": -0.466493371383,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:19.000+0000",
      "lat": 51.4632059216,
      "lon": -0.466464504186,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:16.000+0000",
      "lat": 51.4632059068,
      "lon": -0.466594418466,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:05.000+0000",
      "lat": 51.463223858,
      "lon": -0.466897557232,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:03.000+0000",
      "lat": 51.4632148597,
      "lon": -0.466940859278,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:57:03.000+0000",
      "lat": 51.4632148614,
      "lon": -0.466926424355,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:58.000+0000",
      "lat": 51.4631698748,
      "lon": -0.467099629618,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:57.000+0000",
      "lat": 51.4631698714,
      "lon": -0.467128499436,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:56.000+0000",
      "lat": 51.4631698731,
      "lon": -0.467114064527,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:53.000+0000",
      "lat": 51.4632148183,
      "lon": -0.467287297427,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:52.000+0000",
      "lat": 51.4632148148,
      "lon": -0.467316167272,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:51.000+0000",
      "lat": 51.4632058199,
      "lon": -0.467330599387,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:49.000+0000",
      "lat": 51.4631878265,
      "lon": -0.467388333429,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:47.000+0000",
      "lat": 51.4632417786,
      "lon": -0.467446090069,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:46.000+0000",
      "lat": 51.4632687529,
      "lon": -0.467489403381,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:45.000+0000",
      "lat": 51.4632777426,
      "lon": -0.467518276105,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:44.000+0000",
      "lat": 51.463277739,
      "lon": -0.467547145991,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:43.000+0000",
      "lat": 51.4632867269,
      "lon": -0.467590453678,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:42.000+0000",
      "lat": 51.4632867251,
      "lon": -0.467604888623,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:41.000+0000",
      "lat": 51.4632957148,
      "lon": -0.467633761382,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:38.000+0000",
      "lat": 51.4633586618,
      "lon": -0.46767708636,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:33.000+0000",
      "lat": 51.4634575762,
      "lon": -0.467763727994,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:32.000+0000",
      "lat": 51.4634845576,
      "lon": -0.467749301665,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:29.000+0000",
      "lat": 51.4635475047,
      "lon": -0.467792626982,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:29.000+0000",
      "lat": 51.4635385151,
      "lon": -0.467763754032,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:25.000+0000",
      "lat": 51.4636554249,
      "lon": -0.467778226706,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:23.000+0000",
      "lat": 51.4637003981,
      "lon": -0.46772050088,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:20.000+0000",
      "lat": 51.4637813441,
      "lon": -0.467662786434,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:19.000+0000",
      "lat": 51.4637903409,
      "lon": -0.467633919098,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:12.000+0000",
      "lat": 51.4639252725,
      "lon": -0.467359694308,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:11.000+0000",
      "lat": 51.4639522574,
      "lon": -0.46731639728,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:09.000+0000",
      "lat": 51.4639792474,
      "lon": -0.467229794708,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:04.000+0000",
      "lat": 51.4641141984,
      "lon": -0.4667823451,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:56:00.000+0000",
      "lat": 51.4642221633,
      "lon": -0.466378190735,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:56.000+0000",
      "lat": 51.46442009,
      "lon": -0.46568535378,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:52.000+0000",
      "lat": 51.4646270175,
      "lon": -0.464877030205,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:48.000+0000",
      "lat": 51.4647980012,
      "lon": -0.463693369621,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:46.000+0000",
      "lat": 51.464834021,
      "lon": -0.463159266884,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:43.000+0000",
      "lat": 51.4648700838,
      "lon": -0.46204774544,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:42.000+0000",
      "lat": 51.4648881169,
      "lon": -0.461412589071,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:41.000+0000",
      "lat": 51.4648971172,
      "lon": -0.461311542526,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:38.000+0000",
      "lat": 51.4648702347,
      "lon": -0.459781380313,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:38.000+0000",
      "lat": 51.4648612204,
      "lon": -0.460142265089,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:37.000+0000",
      "lat": 51.4648792594,
      "lon": -0.45920396365,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:34.000+0000",
      "lat": 51.4648343653,
      "lon": -0.457616059727,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:33.000+0000",
      "lat": 51.4648523677,
      "lon": -0.457182998281,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:31.000+0000",
      "lat": 51.4648613962,
      "lon": -0.455999292685,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:30.000+0000",
      "lat": 51.4648614102,
      "lon": -0.45536413315,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:29.000+0000",
      "lat": 51.464861421,
      "lon": -0.454714538171,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:27.000+0000",
      "lat": 51.4648704244,
      "lon": -0.453588573639,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:20.000+0000",
      "lat": 51.4649062867,
      "lon": -0.447958745342,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:17.000+0000",
      "lat": 51.4648791837,
      "lon": -0.44564907644,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:14.000+0000",
      "lat": 51.4649239189,
      "lon": -0.44261762174,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:14.000+0000",
      "lat": 51.4649239567,
      "lon": -0.443050685648,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:55:09.000+0000",
      "lat": 51.4649504223,
      "lon": -0.438200361119,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:52.000+0000",
      "lat": 51.4649653939,
      "lon": -0.422581169474,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:48.000+0000",
      "lat": 51.4649824137,
      "lon": -0.419058900082,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:36.000+0000",
      "lat": 51.4649785435,
      "lon": -0.407611564807,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:35.000+0000",
      "lat": 51.464960253,
      "lon": -0.406832067218,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:32.000+0000",
      "lat": 51.4649229879,
      "lon": -0.403656302349,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:31.000+0000",
      "lat": 51.4649405885,
      "lon": -0.402746848525,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:07.000+0000",
      "lat": 51.4650548733,
      "lon": -0.380516036559,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:01.000+0000",
      "lat": 51.4649434219,
      "lon": -0.375001861371,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:54:00.000+0000",
      "lat": 51.4649698425,
      "lon": -0.37416455794,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:53:50.000+0000",
      "lat": 51.465223537,
      "lon": -0.364203577236,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:53:40.000+0000",
      "lat": 51.4651622906,
      "lon": -0.355037121692,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:53:31.000+0000",
      "lat": 51.4652076404,
      "lon": -0.345206402585,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:53:26.000+0000",
      "lat": 51.4652837097,
      "lon": -0.340023844501,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:52:42.000+0000",
      "lat": 51.4652574649,
      "lon": -0.294032105709,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:52:38.000+0000",
      "lat": 51.4652516066,
      "lon": -0.289759187824,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:52:34.000+0000",
      "lat": 51.4653087715,
      "lon": -0.285644830857,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:52:22.000+0000",
      "lat": 51.4653175693,
      "lon": -0.273331207162,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:52:10.000+0000",
      "lat": 51.4653602236,
      "lon": -0.260512183327,
      "flight": "QAF009"
    }
  },
  {
    "key": "0",
    "value": {
      "_time": "2012-10-12T15:51:33.000+0000",
      "lat": 51.4655240115,
      "lon": -0.217146602515,
      "flight": "QAF009"
    }
  }
]
```


## Test the command:

From the SQL Studio, run the statement:

```sql
SELECT lat, lon, _time, spatialmap(lat,lon,"{\"runway\":[[51.465164,-0.434132],[51.464743,-0.434099],[51.464993,-0.482352],[51.464569,-0.482352]]}")  FROM aircrafts LIMIT 100;
```

The command takes three arguments, the latitude and longitude to geo-map. The third argument is a JSON Array with the coordinates for the polygon. The key name for the array is just a label.

```json
{
  "runway": [
    [
      51.465164,
      -0.434132
    ],
    [
      51.464743,
      -0.434099
    ],
    [
      51.464993,
      -0.482352
    ],
    [
      51.464569,
      -0.482352
    ]
  ]
}
```
