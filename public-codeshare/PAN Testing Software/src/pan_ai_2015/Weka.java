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
	
       String authorIds="";
       Read_and_Write rw= new Read_and_Write();
       
       public void classifyTestSet(String input, String classifier_file_path, String output_file_path) throws Exception
        {
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
                authorIds+=tokenizer.nextToken()+"\t";
                dataset.add(example);
                }
		
		//4.output dataset
		//System.out.println(dataset);
		
		//5.save dataset
		String file = classifier_file_path+"\\feature_file_test.arff";
		ArffSaver saver = new ArffSaver();
		saver.setInstances(dataset);
		saver.setFile(new File(file));
		saver.writeBatch();
		
	
         //6.read dataset
                ArffLoader loader = new ArffLoader();
		loader.setFile(new File(file));
		dataset = loader.getDataSet();	
		
		//7.preprocess strings (almost no classifier supports them)
                
	   /* StringToWordVector filter = new StringToWordVector();
	    filter.setInputFormat(dataset);
	    dataset = Filter.useFilter(dataset, filter);
	    System.out.println(dataset);*/
	    
		//8.build classifier
		dataset.setClassIndex(17);
                
                //10. read classifier back
                String file1=classifier_file_path+"\\classifier.model";
		InputStream is = new FileInputStream(file1);
		ObjectInputStream objectInputStream = new ObjectInputStream(is);
		Classifier classifier = (Classifier) objectInputStream.readObject();
		objectInputStream.close();
		
		//11.evaluate
		
		Instances test = new Instances(dataset, 0, dataset.numInstances());
		test.setClassIndex(17);
		//do eval
		Evaluation eval = new Evaluation(test); //trainset
		eval.evaluateModel(classifier, test); //testset
		System.out.println(eval.toSummaryString());
		System.out.println(eval.weightedFMeasure());
		System.out.println(eval.weightedPrecision());
		System.out.println(eval.weightedRecall());
		/*
		//12.classify
		//result
		System.out.println(classifier.classifyInstance(dataset.firstInstance()));
		//classified result value
		System.out.println(dataset.attribute(dataset.classIndex()).value((int)dataset.firstInstance().classValue()));
		System.out.println(classifier.distributionForInstance(dataset.firstInstance()));
                */
                //output predictions
                StringTokenizer op=new StringTokenizer(authorIds);
                int count=0;
                while(op.hasMoreTokens()){
                     double[] prediction=classifier.distributionForInstance(test.instance(count));
                     count+=1;
                     rw.write_file(op.nextToken()+" "+Double.toString((double) Math.round((prediction[0]) * 1000) / 1000),output_file_path+"\\answers.txt");
                }     
        } 
}
