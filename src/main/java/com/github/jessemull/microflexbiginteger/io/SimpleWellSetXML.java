/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*--------------------------- Package Declaration ----------------------------*/

package com.github.jessemull.microflexbiginteger.io;

/*------------------------------- Dependencies -------------------------------*/

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.jessemull.microflexbiginteger.plate.Well;
import com.github.jessemull.microflexbiginteger.plate.WellSet;

/**
 * This is a wrapper class used to marshal/unmarshal an XML encoded well set in a plate or stack object.
 * 
 * @author Jesse L. Mull
 * @update Updated Oct 17, 2016
 * @address http://www.jessemull.com
 * @email hello@jessemull.com
 */
@XmlRootElement(name="wellset")
public class SimpleWellSetXML {
    
	/*---------------------------- Private fields ----------------------------*/
    
    private String label;               // The set label
    private int size;                   // Size of the data set
    private List<String> wells = 
    		new ArrayList<String>();    // List of well indices
    
    /*----------------------------- Constructors -----------------------------*/
    
    public SimpleWellSetXML(){}
    
    /**
     * Creates a simple XML well set from a well set object.
     * @param    WellSet well    the well set object
     */
    public SimpleWellSetXML(WellSet set) {
        
        this.label = set.label();
        this.size = set.size();
        
        for(Well well : set) {
            wells.add(well.index());
        }
        
    }
    
    /*------------------------- Getters and setters --------------------------*/
    
    /**
     * Sets the size.
     * @param   String type    the numerical type
     */
    @XmlElement
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Sets the list of well indices.
     * @param    List<Strings> wells    the list of well indices
     */
    @XmlElement(name="well")
    @XmlElementWrapper(name="wells")
    public void setWells(List<String> wells) {
    	this.wells = wells;
    }
    
    /**
     * Sets the label.
     * @param    String label    the set label
     */
    @XmlElement
    public void setLabel(String label) {
    	this.label = label;
    }
    
    /**
     * Returns the size of the values array.
     * @return   int   the size of the values array
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Returns the list of well indices.
     * @return    List<String>    the list of well indices
     */
    public List<String> getWells() {
    	return this.wells;
    }
    
    /**
     * Returns the set label.
     * @return    String label    the set label
     */
    public String getLabel() {
    	return this.label;
    }
    
    /**
     * Returns a WellSetBigInteger object.
     * @return    WellSetBigInteger    the well set
     */
    public WellSet toWellSetObject() {
        
    	WellSet set = new WellSet();
        set.setLabel(this.label);
        
        for(String well : wells) {
        	set.add(new Well(well));
        }
        
        return set;
    }
}
