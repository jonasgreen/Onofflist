package com.jg.onofflist;

import com.jg.codegen.CodeGen;

/**
 *
 */
public class OnOffListCodeGen extends CodeGen {
    
    public OnOffListCodeGen() {
    }

    @Override
    public String getSrcDir() {
        return "/Users/jonasgreen/workspace/onofflist/src";
    }

    @Override
    public String getPackageDir() {
        return "/com/jg/onofflist";

    }


    public static void main(String[] args){
        OnOffListCodeGen c = new OnOffListCodeGen();
        c.build();
    }
    
    
}
