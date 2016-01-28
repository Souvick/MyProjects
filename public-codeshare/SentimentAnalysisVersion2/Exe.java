/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

/**
 *
 * @author Souvick
 */
public class Exe {
    // List of variables
    String tagged_output;
    String dependency;
    
    public static void main(String[] args){
        //Trial();
        Test();
    }
    
    public static void Trial(){
        Exe e = new Exe();
        Statistics stat = new Statistics();
        
        SentenceExtractionv2 se2 = new SentenceExtractionv2();
        se2.extractWindow(stat);
        
        GetSelfCitation gsc = new GetSelfCitation();
        gsc.isSelfCitation(stat);
        
        ParserTagging pt = new ParserTagging();
        GetWordAndPOSv2 gwp = new GetWordAndPOSv2();
        POSSequence ps = new POSSequence();
        Unigrams u = new Unigrams();
        Bigrams b = new Bigrams();
        Trigrams t = new Trigrams();
        DependencyFeatures df = new DependencyFeatures();
        SentiWordNet swn = new SentiWordNet();
        CalculateSentiment cs = new CalculateSentiment();
        
        PolarPhraseIdentification p = new PolarPhraseIdentification();
        stat.hmnegatives = p.getNegativePolarityWordsHM();
        stat.hmpositives = p.getPositivePolarityWordsHM();
        
        
        GetPOSFeaturesForClassfn pf = new GetPOSFeaturesForClassfn();
        GetDepFeaturesForClassfn dfc = new GetDepFeaturesForClassfn();
        
        FeaturePrint fp = new FeaturePrint();
        
        for(int i=0; i<stat.globalcounter; i++){
            System.out.println(i);
            stat.taggedoutput[i] = pt.postag(stat.sentence[i]);
            //if (stat.taggedoutput[i]==null) System.out.println("Error postag");
            stat.dependency[i] = pt.dependency(stat.sentence[i]);
            //if (stat.dependency[i]==null) System.out.println("Error dep");
            stat.words[i] = gwp.getWords(stat.taggedoutput[i]);
            //if (stat.words[i]==null) System.out.println("Error words");
            stat.postags[i] = gwp.getPOSTags(stat.taggedoutput[i]);
            //if (stat.postags[i]==null) System.out.println("Error getPostags");
            stat.posfreq[i] = gwp.getPosFrequency(stat.postags[i]);
            //if (stat.posfreq[i]==null) System.out.println("Error getposfreq");
            stat.posseq[i] = ps.getPosSeqFrequency(stat.postags[i]);
            //if (stat.posseq[i]==null) System.out.println("Error posseq");
            stat.bigrams[i] = b.getBigramsWF(stat.words[i]);
            //if (stat.bigrams[i]==null) System.out.println("Error birgams");
            stat.trigrams[i] = t.getTrigramsWF(stat.words[i]);
            //if (stat.trigrams[i]==null) System.out.println("Error trigrams");
            stat.depenrel[i] = df.getDepOutput(stat.dependency[i]);
            //if (stat.depenrel[i]==null) System.out.println("Error depenrel");
            stat.swnscore[i] = swn.obtainScore(stat.taggedoutput[i]);
            //if (stat.taggedoutput[i]==null) System.out.println("Error swn");
            stat.autosentiment[i] = cs.calculateAutoSentiment(stat.swnscore[i]);
                        
            // Obtaining match with positive polar phrases
            stat.unimatchp[i] = u.compareHashmaps(u.getUnigramsWF(stat.words[i]),stat.hmpositives);
            //System.out.println("Unip:"+stat.unimatchp[i]);
            stat.bimatchp[i] = b.compareHashmaps(b.getBigramsWF(stat.words[i]),stat.hmpositives);
            //System.out.println("Bip:"+stat.bimatchp[i]);
            stat.trimatchp[i] = t.compareHashmaps(t.getTrigramsWF(stat.words[i]),stat.hmpositives);
            //System.out.println("Trip:"+stat.trimatchp[i]);
            stat.positivewords[i] = stat.unimatchp[i] + stat.bimatchp[i] + stat.trimatchp[i];
            //System.out.println("Positive:"+stat.positivewords[i]);
            
            // Obtaining match with negative polar phrases
            stat.unimatchn[i] = u.compareHashmaps(u.getUnigramsWF(stat.words[i]),stat.hmnegatives);
            //System.out.println("Unin:"+stat.unimatchn[i]);
            stat.bimatchn[i] = b.compareHashmaps(b.getBigramsWF(stat.words[i]),stat.hmnegatives);
            //System.out.println("Bin:"+stat.bimatchn[i]);
            stat.trimatchn[i] = t.compareHashmaps(t.getTrigramsWF(stat.words[i]),stat.hmnegatives);
            //System.out.println("Trin:"+stat.trimatchn[i]);
            stat.negativewords[i] = stat.unimatchp[i] + stat.bimatchp[i] + stat.trimatchp[i];
            //System.out.println("Negative:"+stat.negativewords[i]);
            
            // Obtaining POS features
            stat.isFWPresent[i] = pf.isFWPresent(stat.posfreq[i]);
            //System.out.println("FW:"+stat.isFWPresent[i]);
            stat.isJJPresent[i] = pf.isJJPresent(stat.posfreq[i]);
            //System.out.println("JJ:"+stat.isJJPresent[i]);
            stat.isRBPresent[i] = pf.isRBPresent(stat.posfreq[i]);
            //System.out.println("RB:"+stat.isRBPresent[i]);
            
            //Obtaining POS Sequence features
            stat.isRBJJPresent[i] = pf.isRBJJPresent(stat.posseq[i]);
            //System.out.println("RB_JJ:"+stat.isRBJJPresent[i]);
            
            //Obtaining Dependency features
            stat.isAmodPresent[i] = dfc.isAmodPresent(stat.depenrel[i]);
            //System.out.println("amod:"+stat.isAmodPresent[i]);
            stat.isAcompPresent[i] = dfc.isAcompPresent(stat.depenrel[i]);
            //System.out.println("acomp:"+stat.isAcompPresent[i]);
            stat.isAdvmodPresent[i] = dfc.isAdvmodPresent(stat.depenrel[i]);
            //System.out.println("advmod:"+stat.isAdvmodPresent[i]);
        }
        
        fp.printfeatures(stat);        
    }
    
    public static void Test(){
        Exe e = new Exe();
        Statistics stat_test = new Statistics();
        
        SentenceExtractionv2 se2_test = new SentenceExtractionv2();
        se2_test.extractWindow(stat_test);
        
        GetSelfCitation gsc = new GetSelfCitation();
        gsc.isSelfCitation(stat_test);
        
        ParserTagging pt = new ParserTagging();
        GetWordAndPOSv2 gwp = new GetWordAndPOSv2();
        POSSequence ps = new POSSequence();
        Unigrams u = new Unigrams();
        Bigrams b = new Bigrams();
        Trigrams t = new Trigrams();
        DependencyFeatures df = new DependencyFeatures();
        SentiWordNet swn = new SentiWordNet();
        CalculateSentiment cs = new CalculateSentiment();
        
        PolarPhraseIdentification p = new PolarPhraseIdentification();
        stat_test.hmnegatives = p.getNegativePolarityWordsHM();
        stat_test.hmpositives = p.getPositivePolarityWordsHM();
        
        
        GetPOSFeaturesForClassfn pf = new GetPOSFeaturesForClassfn();
        GetDepFeaturesForClassfn dfc = new GetDepFeaturesForClassfn();
        
        FeatureTest ft = new FeatureTest();
        
        for(int i=0; i<stat_test.globalcounter; i++){
            System.out.println(i);
            stat_test.taggedoutput[i] = pt.postag(stat_test.sentence[i]);
            //if (stat.taggedoutput[i]==null) System.out.println("Error postag");
            stat_test.dependency[i] = pt.dependency(stat_test.sentence[i]);
            //if (stat.dependency[i]==null) System.out.println("Error dep");
            stat_test.words[i] = gwp.getWords(stat_test.taggedoutput[i]);
            //if (stat.words[i]==null) System.out.println("Error words");
            stat_test.postags[i] = gwp.getPOSTags(stat_test.taggedoutput[i]);
            //if (stat.postags[i]==null) System.out.println("Error getPostags");
            stat_test.posfreq[i] = gwp.getPosFrequency(stat_test.postags[i]);
            //if (stat.posfreq[i]==null) System.out.println("Error getposfreq");
            stat_test.posseq[i] = ps.getPosSeqFrequency(stat_test.postags[i]);
            //if (stat.posseq[i]==null) System.out.println("Error posseq");
            stat_test.bigrams[i] = b.getBigramsWF(stat_test.words[i]);
            //if (stat.bigrams[i]==null) System.out.println("Error birgams");
            stat_test.trigrams[i] = t.getTrigramsWF(stat_test.words[i]);
            //if (stat.trigrams[i]==null) System.out.println("Error trigrams");
            stat_test.depenrel[i] = df.getDepOutput(stat_test.dependency[i]);
            //if (stat.depenrel[i]==null) System.out.println("Error depenrel");
            stat_test.swnscore[i] = swn.obtainScore(stat_test.taggedoutput[i]);
            //if (stat.taggedoutput[i]==null) System.out.println("Error swn");
            stat_test.autosentiment[i] = cs.calculateAutoSentiment(stat_test.swnscore[i]);
                        
            // Obtaining match with positive polar phrases
            stat_test.unimatchp[i] = u.compareHashmaps(u.getUnigramsWF(stat_test.words[i]),stat_test.hmpositives);
            //System.out.println("Unip:"+stat.unimatchp[i]);
            stat_test.bimatchp[i] = b.compareHashmaps(b.getBigramsWF(stat_test.words[i]),stat_test.hmpositives);
            //System.out.println("Bip:"+stat.bimatchp[i]);
            stat_test.trimatchp[i] = t.compareHashmaps(t.getTrigramsWF(stat_test.words[i]),stat_test.hmpositives);
            //System.out.println("Trip:"+stat.trimatchp[i]);
            stat_test.positivewords[i] = stat_test.unimatchp[i] + stat_test.bimatchp[i] + stat_test.trimatchp[i];
            //System.out.println("Positive:"+stat.positivewords[i]);
            
            // Obtaining match with negative polar phrases
            stat_test.unimatchn[i] = u.compareHashmaps(u.getUnigramsWF(stat_test.words[i]),stat_test.hmnegatives);
            //System.out.println("Unin:"+stat.unimatchn[i]);
            stat_test.bimatchn[i] = b.compareHashmaps(b.getBigramsWF(stat_test.words[i]),stat_test.hmnegatives);
            //System.out.println("Bin:"+stat.bimatchn[i]);
            stat_test.trimatchn[i] = t.compareHashmaps(t.getTrigramsWF(stat_test.words[i]),stat_test.hmnegatives);
            //System.out.println("Trin:"+stat.trimatchn[i]);
            stat_test.negativewords[i] = stat_test.unimatchp[i] + stat_test.bimatchp[i] + stat_test.trimatchp[i];
            //System.out.println("Negative:"+stat.negativewords[i]);
            
            // Obtaining POS features
            stat_test.isFWPresent[i] = pf.isFWPresent(stat_test.posfreq[i]);
            //System.out.println("FW:"+stat.isFWPresent[i]);
            stat_test.isJJPresent[i] = pf.isJJPresent(stat_test.posfreq[i]);
            //System.out.println("JJ:"+stat.isJJPresent[i]);
            stat_test.isRBPresent[i] = pf.isRBPresent(stat_test.posfreq[i]);
            //System.out.println("RB:"+stat.isRBPresent[i]);
            
            //Obtaining POS Sequence features
            stat_test.isRBJJPresent[i] = pf.isRBJJPresent(stat_test.posseq[i]);
            //System.out.println("RB_JJ:"+stat.isRBJJPresent[i]);
            
            //Obtaining Dependency features
            stat_test.isAmodPresent[i] = dfc.isAmodPresent(stat_test.depenrel[i]);
            //System.out.println("amod:"+stat.isAmodPresent[i]);
            stat_test.isAcompPresent[i] = dfc.isAcompPresent(stat_test.depenrel[i]);
            //System.out.println("acomp:"+stat.isAcompPresent[i]);
            stat_test.isAdvmodPresent[i] = dfc.isAdvmodPresent(stat_test.depenrel[i]);
            //System.out.println("advmod:"+stat.isAdvmodPresent[i]);
        }
        
        ft.testfeatures(stat_test);        
    }   
    
}
