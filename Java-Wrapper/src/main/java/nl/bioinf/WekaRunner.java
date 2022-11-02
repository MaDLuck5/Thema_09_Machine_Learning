package nl.bioinf;



import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


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
            Classifier fromFile = loadClassifier();
            Instances unknownInstances = loadArff(testFile);
            unknownInstances.setClassIndex(1);
            classifyNewInstance(fromFile, unknownInstances);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void classifyNewInstance(Classifier myClassifier, Instances unknownInstances) throws Exception {
        // create copy
        Instances labeled = new Instances(unknownInstances);
        String[] attributeValues = {"T1", "T2", "T3", "T4"};
        // label instances
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            int maxAt = 0;
            double[] distributions = myClassifier.distributionForInstance(unknownInstances.instance(i));
            // getting the position of the biggest value in the array
            for (int j = 0; j < distributions.length; j++) {
                maxAt = distributions[j] > distributions[maxAt] ? j : maxAt;
            }
            String temp = attributeValues[maxAt];
            labeled.instance(i).setClassValue(maxAt);
            System.out.println("classified as:" + attributeValues[maxAt]);
            System.out.println("Probability distribution for classes:" + Arrays.toString(attributeValues) + Arrays.toString(distributions));
        }
    }

    private Classifier loadClassifier() throws Exception {
        // deserialize model
        String modelFile = "/Model_06_zeror.model";
        try {
            InputStream in = getClass().getResourceAsStream(modelFile);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return (Classifier) SerializationHelper.read(in);
            // Use resource
//             (Classifier) weka.core.SerializationHelper.read(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
