/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SentimentAnalysisVersion2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.StringTokenizer;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
//import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

public class Weka {
	public void createTrainingFeatureFile(String input, String output_file_name) throws Exception {
            String file = output_file_name+"\\feature_file_trial.arff"; 
            ArffLoader loader = new ArffLoader();
           
            //ATTRIBUTES
            Attribute attr[]= new Attribute[50];
            
            //nominal
            /*
            FastVector myNomVals = new FastVector(3);
            myNomVals.addElement("Positive");
            myNomVals.addElement("Neutral");
            myNomVals.addElement("Negative");
            attr[0] = new Attribute("sentiment_cl", myNomVals);
            */
            
            //numeric
            attr[0] = new Attribute("Autosentiment");
            attr[1] = new Attribute("Unimatchp");
            attr[2] = new Attribute("Bimatchp");
            attr[3] = new Attribute("Trimatchp");
            attr[4] = new Attribute("Positivematch");
            attr[5] = new Attribute("Unimatchn");
            attr[6] = new Attribute("Bimatchn");
            attr[7] = new Attribute("Trimatchn");
            attr[8] = new Attribute("Negativematch");
            attr[9] = new Attribute("FW");
            attr[10] = new Attribute("JJ");
            attr[11] = new Attribute("RB");
            attr[12] = new Attribute("RB_JJ");
            attr[13] = new Attribute("amod");
            attr[14] = new Attribute("acomp");
            attr[15] = new Attribute("advmod");
            
            //class
            FastVector classValue = new FastVector(3);
            classValue.addElement("p");
            classValue.addElement("n");
            classValue.addElement("o");
            attr[16] = new Attribute("answer", classValue);
            
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
            //attrs.addElement(attr[17]);
            
            // Add Instances
            Instances dataset = new Instances("my_dataset", attrs, 0);
		
	    if(new File(file).isFile()) {
                loader.setFile(new File(file));
                dataset = loader.getDataSet();	
            }
            
            StringTokenizer tokenizer= new StringTokenizer(input);
            
            while(tokenizer.hasMoreTokens()){
                Instance example = new Instance(17);
                for(int j=0; j<17; j++) {
                    String st= tokenizer.nextToken();
                    System.out.println(j+" "+st);
                    if(j==0)
                        example.setValue(attr[j], Float.parseFloat(st));
                    else if(j==16)
                        example.setValue(attr[j], st);
                    else
                        example.setValue(attr[j], Integer.parseInt(st));
                }
                dataset.add(example);
            }
            
            //Save dataset
            ArffSaver saver = new ArffSaver();
            saver.setInstances(dataset);
            saver.setFile(new File(file));
            saver.writeBatch();
            
            //Read dataset
            loader.setFile(new File(file));
            dataset = loader.getDataSet();	
            
            //Build classifier
            dataset.setClassIndex(16);
            Classifier classifier = new J48();
            classifier.buildClassifier(dataset);
            
            //Save classifier
            String file1=output_file_name+"\\classifier.model";
            OutputStream os = new FileOutputStream(file1);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
            objectOutputStream.writeObject(classifier);		
	      
            
            // Comment out if not needed
            //Read classifier back
            InputStream is = new FileInputStream(file1);
            ObjectInputStream objectInputStream = new ObjectInputStream(is);
            classifier = (Classifier) objectInputStream.readObject();
            objectInputStream.close();
		
            //Evaluate resample if needed
            //dataset = dataset.resample(new Random(42));
            //split to 70:30 learn and test set
            double percent = 70.0;
            int trainSize = (int) Math.round(dataset.numInstances() * percent / 100);
            int testSize = dataset.numInstances() - trainSize;
            Instances train = new Instances(dataset, 0, trainSize);
            Instances test = new Instances(dataset, trainSize, testSize);
            train.setClassIndex(16);
            test.setClassIndex(16);
            
            //Evaluate
            Evaluation eval = new Evaluation(dataset); //trainset
            eval.crossValidateModel(classifier, dataset, 10, new Random(1));
            System.out.println("EVALUATION:\n"+eval.toSummaryString());
            System.out.println("WEIGHTED MEASURE:\n"+eval.weightedFMeasure());
            System.out.println("WEIGHTED PRECISION:\n"+eval.weightedPrecision());
            System.out.println("WEIGHTED RECALL:\n"+eval.weightedRecall());
		
            /*
            //Classify result
            System.out.println("CLASSIFY RESULT:\n"+classifier.classifyInstance(dataset.firstInstance()));
            
            //Classified result value
            System.out.println(dataset.attribute(dataset.classIndex()).value((int)dataset.firstInstance().classValue()));
            System.out.println(classifier.distributionForInstance(dataset.firstInstance()));
            for(int cno=0; cno<120; cno++){
                System.out.println("CNO:"+cno);
                double[] prediction=classifier.distributionForInstance(test.instance(cno));
                //output predictions
                for(int i=0; i<prediction.length; i=i+1){
                    System.out.println("Probability of class "+
                        test.classAttribute().value(i)+
                        " : "+Double.toString(prediction[i]));
                }
            }       
	*/
        } 
        
        public void classifyTestSet(String input, String classifier_file_path, String output_file_path) throws Exception{
            String ids="";
            ReaderWriter rw = new ReaderWriter();
        
            //ATTRIBUTES
            Attribute attr[]= new Attribute[50];
            
            //nominal
            /*
            FastVector myNomVals = new FastVector(3);
            myNomVals.addElement("Positive");
            myNomVals.addElement("Neutral");
            myNomVals.addElement("Negative");
            attr[0] = new Attribute("sentiment_cl", myNomVals);
            */
            
            //numeric
            attr[0] = new Attribute("Autosentiment");
            attr[1] = new Attribute("Unimatchp");
            attr[2] = new Attribute("Bimatchp");
            attr[3] = new Attribute("Trimatchp");
            attr[4] = new Attribute("Positivematch");
            attr[5] = new Attribute("Unimatchn");
            attr[6] = new Attribute("Bimatchn");
            attr[7] = new Attribute("Trimatchn");
            attr[8] = new Attribute("Negativematch");
            attr[9] = new Attribute("FW");
            attr[10] = new Attribute("JJ");
            attr[11] = new Attribute("RB");
            attr[12] = new Attribute("RB_JJ");
            attr[13] = new Attribute("amod");
            attr[14] = new Attribute("acomp");
            attr[15] = new Attribute("advmod");
            
            //class
            FastVector classValue = new FastVector(3);
            classValue.addElement("p");
            classValue.addElement("n");
            classValue.addElement("o");
            attr[16] = new Attribute("answer", classValue);
            
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
            //attrs.addElement(attr[17]);
            
            // Add Instances
            Instances dataset = new Instances("my_dataset", attrs, 0);
            
            StringTokenizer tokenizer= new StringTokenizer(input);
            
            while(tokenizer.hasMoreTokens()){
                Instance example = new Instance(17);
                for(int j=0; j<17; j++) {
                    String st= tokenizer.nextToken();
                    System.out.println(j+" "+st);
                    if(j==0)
                        example.setValue(attr[j], Float.parseFloat(st));
                    else if(j==16)
                        example.setValue(attr[j], st);
                    else
                        example.setValue(attr[j], Integer.parseInt(st));
                }
                ids+=tokenizer.nextToken()+"\t";
                dataset.add(example);
            }
            
            //Save dataset
            String file = classifier_file_path+"\\feature_file_test.arff";
            ArffSaver saver = new ArffSaver();
            saver.setInstances(dataset);
            saver.setFile(new File(file));
            saver.writeBatch();
		
	    //Read dataset
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(file));
            dataset = loader.getDataSet();
            
            //Build classifier
            dataset.setClassIndex(16);
            
            //Read classifier back
            String file1=classifier_file_path+"\\classifier.model";
            InputStream is = new FileInputStream(file1);
            Classifier classifier;
            ObjectInputStream objectInputStream = new ObjectInputStream(is); 
            classifier = (Classifier) objectInputStream.readObject();
            
            //Evaluate
            Instances test = new Instances(dataset, 0, dataset.numInstances());
            test.setClassIndex(16);
            
            //Do eval
            Evaluation eval = new Evaluation(test); //trainset
            eval.evaluateModel(classifier, test); //testset
            System.out.println(eval.toSummaryString());
            System.out.println("WEIGHTED F-MEASURE:"+eval.weightedFMeasure());
            System.out.println("WEIGHTED PRECISION:"+eval.weightedPrecision());
            System.out.println("WEIGHTED RECALL:"+eval.weightedRecall());
            
            //output predictions
            String optest="",val="";
            StringTokenizer op=new StringTokenizer(ids);
            int count=0;
            while(op.hasMoreTokens()){
                double[] prediction=classifier.distributionForInstance(test.instance(count));
                count+=1;
                //optest+=op.nextToken()+" "+Double.toString((double) Math.round((prediction[0]) * 1000) / 1000)+"\n";                
                if (prediction[0]>prediction[1]){
                    if(prediction[0]>prediction[2]){
                        val = "p: " + Double.toString((double) Math.round((prediction[0]) * 1000) / 1000);
                    }
                    else{
                        val = "o: " +Double.toString((double) Math.round((prediction[2]) * 1000) / 1000);
                    }                    
                }
                else{
                    if(prediction[1]>prediction[2]){
                        val = "n: " + Double.toString((double) Math.round((prediction[1]) * 1000) / 1000);
                    }
                    else{
                        val = "o: " +Double.toString((double) Math.round((prediction[2]) * 1000) / 1000);
                    }
                }
                optest += op.nextToken() + "\t" + val + "\n";
            }
            rw.writeToFile(optest, "E:\\op\\CSS\\", "Answers", "Test", "txt");                
        }
}
