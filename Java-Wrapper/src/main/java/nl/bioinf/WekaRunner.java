package nl.bioinf;



import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;


public class WekaRunner {

    public static void main(String[] args) {
        WekaRunner runner = new WekaRunner();
        runner.start(args);
    }

    private void start(String[] args) {
        //String datafile = args[0];
        String testFile = args[0];
        System.out.println("Data File loaded:" + testFile);
        try {
            //Instances instances = loadArff(datafile);
            //printInstances(instances);
            //AttributeSelectedClassifier attributeselected = buildClassifier(instances);
            //saveClassifier(attributeselected);
            Classifier fromFile = loadClassifier();
            Instances unknownInstances = loadArff(testFile);
            unknownInstances.setClassIndex(1);
            //System.out.println("\nunclassified unknownInstances = \n" + unknownInstances);
            classifyNewInstance(fromFile, unknownInstances);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void classifyNewInstance(Classifier tree, Instances unknownInstances) throws Exception {
        // create copy
        Instances labeled = new Instances(unknownInstances);
        String[] attributeValues = {"T1", "T2", "T3", "T4"};
        // label instances
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            int maxAt = 0;
            double[] distributions = tree.distributionForInstance(unknownInstances.instance(i));

            System.out.println(unknownInstances.instance(i));

            // getting the position of the biggest value in the array
            for (int j = 0; j < distributions.length; j++) {
                maxAt = distributions[j] > distributions[maxAt] ? j : maxAt;
            }

            String temp = attributeValues[maxAt];

            labeled.instance(i).setClassValue(maxAt);
            System.out.println("instance" + labeled.instance(i) + "classified as:" + attributeValues[maxAt]);

        }

    }




    private Classifier loadClassifier() throws Exception {
        // deserialize model
        String modelFile = "src/main/resources/Model-01_27_10.model";
        return (Classifier) weka.core.SerializationHelper.read(modelFile);
    }


    private Instances loadArff(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }
}
