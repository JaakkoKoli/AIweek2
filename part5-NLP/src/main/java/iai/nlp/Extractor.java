package iai.nlp;

import java.util.LinkedList;
import opennlp.tools.parser.Parse;

public class Extractor {
    
    /**
     * Finds the subject of a sentence from a syntax tree.     * 
     * @param root root of the tree (S)
     * @return Subject as a String
     */
    public static String extractSubject(Parse root) {
        // Implement finding the suject as follows:
        // 1. Choose a root's child node with POS NP.
        // 2. Do a BFS search on the subtree of the chosen child.
        // 3. Subject is in the first found node whose POS-tag is a noun.
        // POS tags that are nouns: NN, NNP, NNS, NNPS.
        // If no noun is found, return null.
        // 
        // Useful methods in Parse objects:
        // node.getChilderen()
        // node.getType()
        // node.getCoveredText()
        Parse cur = null;
        for(Parse p:root.getChildren()){
            if(p.getType().equals("NP")){
                cur=p;
            }
        }
        if(cur==null) return null;
        LinkedList<Parse> q=new LinkedList<>();
        q.add(cur);
        while(!q.isEmpty()){
            cur=q.poll();
            if(cur.getType().equals("NN")||cur.getType().equals("NNP")||cur.getType().equals("NNS")||cur.getType().equals("NNPS")){
                return cur.getCoveredText();
            }
            for(Parse p:cur.getChildren()){
                q.add(p);
            }
        }
        return null;
    }
    
}
