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

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.github.jessemull.microflexbiginteger.plate.Well;

/**
 * This is a wrapper class used to marshal/unmarshal an XML encoded well in a result.
 * 
 * @author Jesse L. Mull
 * @update Updated Oct 17, 2016
 * @address http://www.jessemull.com
 * @email hello@jessemull.com
 */
@XmlRootElement(name="well")
@XmlType (propOrder={"index", "value"})
public class ResultWellXML {
    
	/*---------------------------- Private Fields ----------------------------*/

    private String index;        // The well index
    private BigInteger value;    // The value
   
    /*------------------------------ Constructors ----------------------------*/
    
    public ResultWellXML(){}
    
    /**
     * Creates a simple XML well from an index and value.
     * @param    Well well   the well object
     */
    public ResultWellXML(String index, BigInteger value) {
        this.index = index;
        this.value = value;
    }
    
    /*-------------------------- Getters and setters -------------------------*/
   
    /**
     * Sets the index.
     * @param    String newIndex    the new index
     */
    @XmlElement
    public void setIndex(String newIndex) {
        this.index = newIndex;
    }
    
    /**
     * Sets the value.
     * @param    BigInteger value    the new value
     */
    @XmlElement
    public void setValue(BigInteger value) {
        this.value = value;
    }

    /**
     * Returns the index.
     * @return    String    the index
     */
    public String getIndex() {
        return this.index;
    }
    
    /**
     * Returns the value.
     * @return    BigInteger    the value
     */
    public BigInteger getValue() {
        return this.value;
    }
    
    /**
     * Returns a WellBigInteger object.
     * @return    WellBigInteger    the well
     */
    public Well toWellObject() {
        
    	Well well = new Well(this.index);
    	well.add(this.value);
    	
    	return well;
    	
    }
}
