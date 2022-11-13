# Weka Model wrapper

## Purpose

To classify new instances of a data set containing log2 iTRAQ rations for individula proteins, en report the classification of which tumor stage it wel likely be.

## Installation: 
Make sure Java 17 is installed on your system.
And Weka 3.8.6 is installed on your system.

## Usage:
make sure the the data is in the following format:
with each value being a Log2 iTRAQ ratio for a protein 

instance | #NP_958782 | #NP_958785 | NP_958786 | NP_000436 | #NP_958781 | #NP_958780 | NP_958783 | ETC...
--- | --- | --- | --- |--- |--- |--- |--- | ---
1 |  0.6834035 | 0.6944241 290 | 0.6980976 |0.68707705 | 0.6870771 | 0.6980976 |  0.6980976 | ETC...

and the file is created in R or weka in a .arff format.

to classify a new instance, use the following command:
```java -jar <program_name> <path_to_instance>```

#### Example
```java -jar .\MLModelWrapperApplication1-1.0.2-all.jar C:\Users\matsp\Documents\Thema-09\Project_thema_09\Analysis\data\train3.arff```


## files
MLModelWrapperApplication1-1.0.2-all.jar found in ```./build/libs/``` the Java wrapper in a Jar

train3.arff: a arff file in the correct format for usage with the build java wrapper found in ```../Analysis/data/```
## Support

For Help, contact: M.p.slik@st.hanze.nl

## Authors and acknowledgment  

Thanks to the developers of python, biopython, the people at NCBI & The Hanze Hogeschool Groningen Bio-informatics staff. 





