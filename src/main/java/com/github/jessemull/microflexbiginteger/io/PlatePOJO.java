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

import com.github.jessemull.microflexbiginteger.plate.Plate;
import com.github.jessemull.microflexbiginteger.plate.Well;
import com.github.jessemull.microflexbiginteger.plate.WellSet;

/**
 * This is a wrapper class for importing or exporting a JSON encoded plate object.
 * 
 * @author Jesse L. Mull
 * @update Updated Oct 17, 2016
 * @address http://www.jessemull.com
 * @email hello@jessemull.com
 */
public class PlatePOJO {
    
	/*---------------------------- Private fields ----------------------------*/

    private String type;                                   // Data set numerical type
    private String label;                                  // Plate label
    private String descriptor;                             // Plate descriptor
    private int rows;                                      // Number of plate rows
    private int columns;                                   // Number of plate columns 
    private int size;                                      // Number of plate wells   
    private List<SimpleWellSetPOJO> wellsets = 
    		new ArrayList<SimpleWellSetPOJO>();    // Plate well sets
    private List<SimpleWellPOJO> wells = 
    		new ArrayList<SimpleWellPOJO>();       // Plate wells
    
    /*----------------------------- Constructors -----------------------------*/ 
    
    public PlatePOJO(){}
    
    /**
     * Creates a plate POJO from a plate object.
     * @param    Plate plate    the plate
     */
    public PlatePOJO(Plate plate) {
        
        this.type = "BigInteger";
        this.label = plate.label();
        this.descriptor = plate.descriptor();
        this.rows = plate.rows();
        this.columns = plate.columns();
        this.size = plate.size();
        
        for(WellSet set : plate.allGroups()) {
            wellsets.add(new SimpleWellSetPOJO(set));
        }
        
        for(Well well : plate) {
            wells.add(new SimpleWellPOJO(well));
        }
    }
    
    /*----------------------------- Constructors -----------------------------*/
    
    /**
     * Sets the data type.
     * @param    String type    the data set numerical type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Sets the stack label.
     * @param    String label    the stack label
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Sets the row number.
     * @param   int rows       number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    /**
     * Sets the column number.
     * @param   int columns    number of columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Sets the size.
     * @param   int size    number of plates in the stack
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Sets the plate descriptor.
     * @param   String descriptor    the plate descriptor
     */
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
    
    /**
     * Sets the list of well sets.
     * @param    List<SimpleWellSetPOJOBigInteger> sets    list of plate well sets
     */
    public void setWellsets(List<SimpleWellSetPOJO> wellsets) {
    	this.wellsets = wellsets;
    }
    
    /**
     * Sets the list of wells.
     * @param    List<SimpleWellPOJOBigInteger> wells    list of plate wells
     */
    public void setWells(List<SimpleWellPOJO> wells) {
    	this.wells = wells;
    }
    
    /**
     * Returns the data type.
     * @return    String    the data set numerical type
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Returns the stack label.
     * @return    String    the stack label
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * Returns the row number.
     * @return   int    number of rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Returns the column number.
     * @return   int    number of columns
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Returns the size.
     * @return   int    number of plates in the stack
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Returns the descriptor.
     * @return   String    plate descriptor
     */
    public String getDescriptor() {
        return this.descriptor;
    }
    
    /**
     * Returns the list of well sets.
     * @return   List<SimpleWellSetPOJOBigInteger>    returns the plate well sets
     */
    public List<SimpleWellSetPOJO> getWellsets() {
        return this.wellsets;
    }
    
    /**
     * Returns the list of wells.
     * @return   List<SimpleWellPOJOBigInteger>    list of plate wells
     */
    public List<SimpleWellPOJO> getWells() {
        return this.wells;
    }
    
    /**
     * Returns a PlateBigInteger object.
     * @return    PlateBigInteger    the plate
     */
    public Plate toPlateObject() {

        Plate plate = new Plate(this.rows, this.columns, this.label);
        
        for(SimpleWellSetPOJO set : this.wellsets) {
        	plate.addGroups(set.toWellSetObject().wellList());
        }
        
        for(SimpleWellPOJO well : wells) {
        	plate.addWells(well.toWellObject());
        }
        
        return plate;
    }
}
