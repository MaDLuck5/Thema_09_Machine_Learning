/*
 * Copyright (c) 2022 Mats Slik.
 * Licensed under GPLv3. See LICENSE file.
 */

package nl.bioinf;


import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Main class for the Java wrapper around the machine learning model.
 * Designed to work with user input provided through the command line.
 * The class is final since it is not made to be extended.
 *
 * @author Mats SLik (344216)
 */
public class WekaRunner {
    /**
     * initialises the model and runs the prediction.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        WekaRunner runner = new WekaRunner();
        runner.start(args);
    }
    /**
     * Class that uses and implements part of the Weka Java API to classify instances.
     * it reads the model from a .arff file and uses it to classify instances.
     * @param args The command line argument.
     */
    public void start(String[] args) {
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
    /**
     * @param myClassifier The classifier to use for the classification.
     * @param unknownInstances a .arff file containing instances of protein expression data to be classified by the model.
     * Prints the classified instances with the probability of the prediction.
     * @throws Exception if the classification fails.
     */
    public void classifyNewInstance(Classifier myClassifier, Instances unknownInstances) throws Exception {
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
            System.out.println("ID: " + labeled.instance(i).value(1) + ", Classified as: " + attributeValues[maxAt]);
            System.out.println(" Probability distribution for classes:" + Arrays.toString(attributeValues) + Arrays.toString(distributions));
        }
    }
    /**
     * Loads the model from a .arff file.
     */
    private Classifier loadClassifier() throws Exception {
        // deserialize model
        String modelFile = "/Model-01_27_10.model";
        try {
            InputStream in = getClass().getResourceAsStream(modelFile);
            return (Classifier) SerializationHelper.read(in);
            // Use resource
//
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the .arff file containing the instances to be classified.
     */
    private Instances loadArff(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }
}
