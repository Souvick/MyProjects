/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

/**
 *
 * @author Promita
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Weka {
	public void createTrainingFeatureFile(String input, String output_file_name) throws Exception {
		
            String file = output_file_name+"\\feature_file_trial.arff"; 
            ArffLoader loader = new ArffLoader();
           
            
            //1.ATTRIBUTES
		Attribute attr[]= new Attribute[50];
		//nominal
		FastVector myNomVals = new FastVector(4);
		myNomVals.addElement("Dutch");
                myNomVals.addElement("English");
                myNomVals.addElement("Greek");
                myNomVals.addElement("Spanish");
		 attr[0] = new Attribute("language", myNomVals);
				
                //numeric
                 attr[1] = new Attribute("punctuationtoword_ratio");
		 attr[2] = new Attribute("comma_ratio");
		 attr[3] = new Attribute("semicolon_ratio");
                 attr[4] = new Attribute("colon_ratio");
                 attr[5] = new Attribute("stop_ratio");
                 attr[6] = new Attribute("question_ratio");
                 attr[7] = new Attribute("exclamation_ratio");
                 attr[8] = new Attribute("slash_ratio");
                 attr[9] = new Attribute("dash_ratio");
                 attr[10] = new Attribute("shortsentence_ratio");
                 attr[11] = new Attribute("longsentence_ratio");
                 attr[12] = new Attribute("uniqueword_ratio");
                 attr[13] = new Attribute("posfreq");
                 attr[14] = new Attribute("bigram");
                 attr[15] = new Attribute("trigram");
                 attr[16] = new Attribute("posseqfreq");
		
                //class
                FastVector classValue = new FastVector(2);
		classValue.addElement("Y");
                classValue.addElement("N");
                attr[17] = new Attribute("answer", classValue);
                
		//string
		//Attribute attr2 = new Attribute("my-string", (FastVector)null);
		//System.out.println(attr2.isString());
		
		//date
		//Attribute attr3 = new Attribute("my-date", "dd-MM-yyyy");
		//System.out.println(attr3.isDate());
		
		//whole relation can also be an attr
		//Attribute attr4 = new Attribute("my-relation", new Instances(...));
		
		//2.create dataset
		FastVector attrs = new FastVector();
		
                attrs.addElement(attr[0]);
		attrs.addElement(attr[1]);
		attrs.addElement(attr[2]);
		attrs.addElement(attr[3]);
                attrs.addElement(attr[4]);
                attrs.addElement(attr[5]);
		attrs.addElement(attr[6]);
		attrs.addElement(attr[7]);
                attrs.addElement(attr[8]);
                attrs.addElement(attr[9]);
		attrs.addElement(attr[10]);
		attrs.addElement(attr[11]);
                attrs.addElement(attr[12]);
                attrs.addElement(attr[13]);
                attrs.addElement(attr[14]);
                attrs.addElement(attr[15]);
                attrs.addElement(attr[16]);
                attrs.addElement(attr[17]);
                
		Instances dataset = new Instances("my_dataset", attrs, 0);
		
		//3.add instances
                /*FileInputStream fstream = new FileInputStream(input_file_name);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                
                String lines[] = new String[236600];
		String str;
                int c = 0;
                while((str = br.readLine())!=null)
                    lines[c++] = str;
                //int no_of_words = lines[0].split("\t").length-2;
                ArrayList label = new ArrayList();
                for(int i = 0;i<c;i++)
                {
                String arr[] = lines[i].split("\t");
                if(!label.contains(arr[arr.length-1]))
                    label.add(arr[arr.length-1]);
                }*/
                if(new File(file).isFile())
                {
                    loader.setFile(new File(file));
                    dataset = loader.getDataSet();	
                }
                
               StringTokenizer tokenizer= new StringTokenizer(input);
                while(tokenizer.hasMoreTokens()){
                Instance example = new Instance(18);
                for(int j=0; j<=17; j++)
                {
                        String st= tokenizer.nextToken();
                        System.out.println(j+" "+st);
                        if(j==0 || j==17)
                            example.setValue(attr[j], st);
                        else
                            example.setValue(attr[j], Float.parseFloat(st));
                }
                dataset.add(example);
                }
		
		//4.output dataset
		//System.out.println(dataset);
		
		//5.save dataset
		//String file = output_file_name+"\\feature_file_trial.arff";
		ArffSaver saver = new ArffSaver();
		saver.setInstances(dataset);
		saver.setFile(new File(file));
		saver.writeBatch();
		
	
         //6.read dataset
                
		loader.setFile(new File(file));
		dataset = loader.getDataSet();	
		
		//7.preprocess strings (almost no classifier supports them)
	   /* StringToWordVector filter = new StringToWordVector();
	    filter.setInputFormat(dataset);
	    dataset = Filter.useFilter(dataset, filter);
	    System.out.println(dataset);*/
	    
		//8.build classifier
		dataset.setClassIndex(17);
		Classifier classifier = new RandomForest();
		classifier.buildClassifier(dataset);
                 
		
		//9.save classifier
                String file1=output_file_name+"\\classifier.model";
		OutputStream os = new FileOutputStream(file1);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.writeObject(classifier);
		
		//10. read classifier back
		/*InputStream is = new FileInputStream(file1);
		ObjectInputStream objectInputStream = new ObjectInputStream(is);
		classifier = (Classifier) objectInputStream.readObject();
		objectInputStream.close();
		
		//11.evaluate
		//resample if needed
		//dataset = dataset.resample(new Random(42));
		//split to 70:30 learn and test set
		double percent = 70.0;
		int trainSize = (int) Math.round(dataset.numInstances() * percent / 100);
		int testSize = dataset.numInstances() - trainSize;
		Instances train = new Instances(dataset, 0, trainSize);
		Instances test = new Instances(dataset, trainSize, testSize);
		train.setClassIndex(17);
		test.setClassIndex(17);
		//do eval
		Evaluation eval = new Evaluation(dataset); //trainset
                eval.crossValidateModel(classifier, dataset, 10, new Random(1));
		System.out.println(eval.toSummaryString());
		System.out.println(eval.weightedFMeasure());
		System.out.println(eval.weightedPrecision());
		System.out.println(eval.weightedRecall());
		
		//12.classify
		//result
		System.out.println(classifier.classifyInstance(dataset.firstInstance()));
		//classified result value
		System.out.println(dataset.attribute(dataset.classIndex()).value((int)dataset.firstInstance().classValue()));
		System.out.println(classifier.distributionForInstance(dataset.firstInstance()));
                for(int cno=0; cno<120; cno++){
                double[] prediction=classifier.distributionForInstance(test.instance(cno));

        //output predictions
        for(int i=0; i<prediction.length; i=i+1)
        {
            System.out.println("Probability of class "+
                                test.classAttribute().value(i)+
                               " : "+Double.toString(prediction[i]));
        }
                }       
	
        */
        }
        
}
