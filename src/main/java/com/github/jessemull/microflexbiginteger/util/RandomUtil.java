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

/* -------------------------- Package Declaration --------------------------- */

package com.github.jessemull.microflexbiginteger.util;

/* ------------------------------ Dependencies ------------------------------ */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.github.jessemull.microflexbiginteger.plate.Plate;
import com.github.jessemull.microflexbiginteger.plate.Stack;
import com.github.jessemull.microflexbiginteger.plate.Well;
import com.github.jessemull.microflexbiginteger.plate.WellSet;

/**
 * This class provides static utility helper methods for generating random data
 * sets, wells, sets, plates and stacks.
 * 
 * @author Jesse L. Mull
 * @update Updated Oct 18, 2016
 * @address http://www.jessemull.com
 * @email hello@jessemull.com
 */
public class RandomUtil {

	/*---------------------------- Public Fields -----------------------------*/
	
	public final int DOUBLE = 0;
	public final int INTEGER = 1;
	public final int BIGDECIMAL = 2;
	public final int BIGINTEGER = 3;
    
    /*------------ Methods for Random PlateBigInteger Generation -------------*/
    
    /**
     * Returns a random well between the indices.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum row index
     * @param    int           maximum row index
     * @param    int           minimum column index
     * @param    int           maximum column index
     * @param    int           minimum list length
     * @param    int           maximum list length
     * @return                 the well
     */
    public static Well randomWellBigInteger(BigInteger min, 
                                                      BigInteger max, 
                                                      int rowMin, 
                                                      int rowMax, 
                                                      int columnMin, 
                                                      int columnMax, 
                                                      int minLength, 
                                                      int maxLength) {
    	try {
	    	
    		if(min.compareTo(max) > 0  ||
	 	       rowMin > rowMax         ||
	 	       columnMin > columnMax   ||
	 	       rowMin < 0              ||
	 	       rowMax < 0              ||
	 	       columnMin < 1           ||
	 	       columnMax <= 1          ||
	 	       minLength >= maxLength) {
	 	           throw new IndexOutOfBoundsException("Index out of bounds creating random big integer well.");
	 	    }
	    	
    		Random random = new Random();
    		
    		int row = rowMin + random.nextInt((rowMax - rowMin - 1) + 1);
            int column = columnMin + random.nextInt((columnMax - columnMin) + 1);
	        
	        List<BigInteger> list = randomBigIntegerList(min, max, minLength, maxLength);	        
	        Well well = new Well(row, column, list);

	        return well;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    /**
     * Returns a random well between the indices.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum row index
     * @param    int           maximum row index
     * @param    int           minimum column index
     * @param    int           maximum column index
     * @param    int           number of data points
     * @return                 the well
     */
    public static Well randomWellBigInteger(BigInteger min, 
                                                      BigInteger max, 
                                                      int rowMin, 
                                                      int rowMax, 
                                                      int columnMin, 
                                                      int columnMax, 
                                                      int length) {
    	try {
	    	
    		if(min.compareTo(max) > 0  ||
	 	       rowMin > rowMax         ||
	 	       columnMin > columnMax   ||
	 	       rowMin < 0              ||
	 	       rowMax < 0              ||
	 	       columnMin < 1           ||
	 	       columnMax <= 1) {
	 	           throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger well.");
	 	    }
	   
    		Random random = new Random();
    		
    		int row = rowMin + random.nextInt((rowMax - rowMin - 1) + 1);
            int column = columnMin + random.nextInt((columnMax - columnMin) + 1);
	        
	        List<BigInteger> list = randomBigIntegerList(min, max, length);	        
	        Well well = new Well(row, column, list);

	        return well;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * Returns a random well set with wells between the indices.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum row index
     * @param    int           maximum column index
     * @param    int           minimum column index
     * @param    int           maximum column index
     * @param    int           minimum number of wells in set
     * @param    int           maximum number of wells in set
     * @return                 the well set
     */
    public static WellSet randomWellSetBigInteger(BigInteger min, 
                                                            BigInteger max, 
                                                            int rowMin, 
                                                            int rowMax, 
                                                            int columnMin, 
                                                            int columnMax, 
                                                            int minLength, 
                                                            int maxLength) {
        try {
        	
        	if(min.compareTo(max) > 0   ||
     	 	   rowMin > rowMax          ||
     	       columnMin > columnMax    ||
     	       rowMin < 0               ||
  	 	       rowMax < 0               ||
  	 	       columnMin < 1            ||
   	 	       columnMax <= 1           ||
     	 	   minLength >= maxLength) {
     	 	       throw new IndexOutOfBoundsException("Index out of bounds creating random big integer well set.");
     	    }	    	   
	    	
	        WellSet set = new WellSet();
	        
	        for(int i = minLength; i < maxLength; i++) {
	            set.add(randomWellBigInteger(min, max, rowMin, rowMax, columnMin, 
	                                          columnMax, minLength, maxLength));   
	        }
	        
	        return set;
        
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
    
    /**
     * Returns a random well set with wells between the indices.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum row index
     * @param    int           maximum column index
     * @param    int           minimum column index
     * @param    int           maximum column index
     * @param    int           the number of wells
     * @return                 the well set
     */
    public static WellSet randomWellSetBigInteger(BigInteger min, 
                                                            BigInteger max, 
                                                            int rowMin, 
                                                            int rowMax, 
                                                            int columnMin, 
                                                            int columnMax, 
                                                            int length) {
        try {
        	
        	if(min.compareTo(max) > 0   ||
     	 	   rowMin > rowMax          ||
     	       columnMin > columnMax    ||
     	       rowMin < 0               ||
  	 	       rowMax < 0               ||
  	 	       columnMin < 1            ||
   	 	       columnMax < 1) {
     	 	       throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger well set.");
     	    }	    	   
   	
	        WellSet set = new WellSet();

	        while(set.size() < length) {
	            set.add(randomWellBigInteger(min, max, rowMin, rowMax, columnMin, 
	                                          columnMax, length));   
	        }
	        
	        return set;
        
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
    
    /**
     * Generates random BigInteger data sets for each well in the plate using the
     * minimum and maximum data point values and the minimum and maximum length
     * values.
     * @param     int           the plate type
     * @param     BigInteger    minimum data point value
     * @param     BigInteger    maximum data point value
     * @param     int           minimum data set length
     * @param     int           maximum data set length
     * @param     int           the number of groups
     * @param     int           the number of wells
     * @param     String        the plate label
     * @return                  the plate containing random data sets
     */
    public static Plate randomPlateBigInteger(int rows,
    													int columns,
                                                        BigInteger min, 
                                                        BigInteger max, 
                                                        int minLength, 
                                                        int maxLength, 
                                                        int groupNumber,
                                                        int wellNumber,
                                                        String label) {
        
    	try {
    		if(min.compareTo(max) > 0   ||
   	 	       groupNumber < 0          ||
   	 	       wellNumber < 0           ||
   	 	       rows < 0                 ||
   	 	       columns < 0              ||
   	 	       minLength >= maxLength) {
       	 	       throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger plate.");
            }

    		Plate plate = new Plate(rows, columns, label);

            while(plate.allGroups().size() < groupNumber) {
            	
            	WellSet set = null;
            	
            	try {
            	    set = randomWellSetBigInteger(
            	        min, max, 0, plate.rows(), 1, plate.columns(), 1, wellNumber);
            	    plate.addGroups(set.wellList());
            	} catch(Exception e) {
            		System.err.println("Failed to add group " + set.wellList() + " already exists in the data set.");
            	}
            }

            WellSet set = randomWellSetBigInteger(
            		min, max, 0, plate.rows(), 1, plate.columns(), 1, wellNumber);
            plate.addWells(set);
            
            return plate;
            
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
        
    }
    
    /**
     * Generates random BigInteger data sets for each well in the plate using the
     * minimum and maximum data point values and the minimum and maximum length
     * values.
     * @param     int           the number of rows
     * @param     int           the number of columns
     * @param     BigInteger    minimum data point value
     * @param     BigInteger    maximum data point value
     * @param     int           the number of wells
     * @param     String        the plate label
     * @return                  the plate containing random data sets
     */
    public static Plate randomPlateBigInteger(int rows,
    													int columns,
                                                        BigInteger min, 
                                                        BigInteger max, 
                                                        int wellNumber,
                                                        String label) {
        
    	try {
    		if(min.compareTo(max) > 0   ||
   	 	       wellNumber < 0           ||
   	 	       rows < 0                 ||
   	 	       columns < 0) {
       	 	       throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger plate.");
            }

    		Plate plate = new Plate(rows, columns, label);
            WellSet set = randomWellSetBigInteger(
            		min, max, 0, plate.rows(), 1, plate.columns(), wellNumber);
            
            plate.addWells(set);
            
            return plate;
            
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
        
    }
    
    /**
     * Generates a random BigInteger stack with the specified number of plates.
     * Creates plates for the stack with each well in the plate using the minimum 
     * and maximum data point values and the minimum and maximum length values.
     * @param    int           number of plate rows
     * @param    int           number of plate columns
     * @param    BigInteger    minimum data set value
     * @param    BigInteger    maximum data set value
     * @param    int           minimum well data set length
     * @param    int           maximum well data set length
     * @param    int           the number of sets in each plate
     * @param    int           the number of wells in each plate
     * @param    String        the stack label
     * @param    int           the number of plates in the stack
     * @return                 the randomly generated stack
     */
    public static Stack randomStackBigInteger(int rows,
    													int columns,
                                                        BigInteger min, 
                                                        BigInteger max, 
                                                        int minLength, 
                                                        int maxLength, 
                                                        int setNumber,
                                                        int wellNumber,
                                                        String label,
                                                        int plateNumber) {
        
    	try {
    		
    		if(min.compareTo(max) > 0   ||
    	   	   setNumber < 0            ||
    	   	   minLength >= maxLength   ||
    	   	   label == null            ||
    	   	   plateNumber < 0          ||
    	   	   rows < 0                 ||
    	   	   columns < 0              ||
    	   	   minLength >= maxLength) {
    	           throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger stack.");
    	    }
    		
    		Stack stack = new Stack(randomPlateBigInteger(rows, columns, min, max, minLength, maxLength, setNumber, wellNumber, label));
	        
	        for(int i = 1; i < plateNumber; i++) {
	            stack.add(randomPlateBigInteger(rows, columns, min, max, minLength, maxLength, setNumber, wellNumber, "Plate" + i));
	        }
	        
	        return stack;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    		
        
    }
    
    /**
     * Generates a random BigInteger stack with the specified number of plates.
     * Creates plates for the stack with each well in the plate using the minimum 
     * and maximum data point values and the minimum and maximum length values.
     * @param    int           number of plate rows
     * @param    int           number of plate columns
     * @param    BigInteger    minimum data set value
     * @param    BigInteger    maximum data set value
     * @param    int           the number of wells in each plate
     * @param    String        the stack label
     * @param    int           the number of plates in the stack
     * @return                 the randomly generated stack
     */
    public static Stack randomStackBigInteger(int rows,
    													int columns,
                                                        BigInteger min, 
                                                        BigInteger max, 
                                                        int wellNumber,
                                                        String label,
                                                        int plateNumber) {
        
    	try {
    		
    		if(min.compareTo(max) > 0   ||
    	   	   label == null            ||
    	   	   plateNumber < 0          ||
    	   	   rows < 0                 ||
    	   	   columns < 0              ) {
    	           throw new IndexOutOfBoundsException("Index out of bounds creating random BigInteger stack.");
    	    }
    		
    		Stack stack = new Stack(rows, columns);
	        
	        for(int i = 1; i < plateNumber; i++) {
	            stack.add(randomPlateBigInteger(rows, columns, min, max, wellNumber, label));
	        }
	        
	        return stack;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    		
        
    }
    
    /**
     * Returns a list of randomly generated BigIntegers using the minimum and maximum
     * data point values and the minimum and maximum lengths.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum list length
     * @param    int           maximum list length
     * @return                 list with random BigInteger values
     */
    public static List<BigInteger> randomBigIntegerList(BigInteger min, BigInteger max, int minLength, int maxLength) {
        
    	try {
    		
	    	if(min.compareTo(max) > 0  ||
	 	 	   minLength >= maxLength) {
	 	 	    throw new IndexOutOfBoundsException("Index out of bounds generating random big integer list.");
	 	    }
	    	
	    	List<BigInteger> list = new ArrayList<BigInteger>();
	        
	        int length = ThreadLocalRandom.current().nextInt(minLength, maxLength + 1);
	        
	        for(int i = 0; i < length; i++) {
	            Random random = new Random();
	            int maxBitLength = max.bitLength();
	            BigInteger randomBigInt;
	            do {
	                randomBigInt = new BigInteger(maxBitLength, random);
	            } while(randomBigInt.compareTo(max) >= 0 && randomBigInt.compareTo(min) <= 0);
	            list.add(randomBigInt);
	        }
	        
	        return list;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
        
    }

    /**
     * Returns a list of randomly generated BigIntegerss using the minimum and maximum
     * data point values and the minimum and maximum lengths.
     * @param    BigInteger    minimum data point value
     * @param    BigInteger    maximum data point value
     * @param    int           minimum list length
     * @param    int           maximum list length
     * @return                 list with random BigInteger values
     */
    public static List<BigInteger> randomBigIntegerList(BigInteger min, BigInteger max, int length) {
        
    	try {

    		Random random = new Random();
    		
	    	if(min.compareTo(max) > 0  || length <= 0) {
	 	 	    throw new IndexOutOfBoundsException("Index out of bounds generating random big decimal list.");
	 	    }
	    	
	        List<BigInteger> list = new ArrayList<BigInteger>();

	        for(int i = 0; i < length; i++) {

	        	BigInteger toAdd;
	        	
	        	do {
	        		toAdd = new BigInteger(max.bitLength(), random);
	        	} while(toAdd.compareTo(min) <= 0 || toAdd.compareTo(max) >= 0);

	        	list.add(toAdd);
	        }
	        
	        return list;
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
        
    }
    
    /*---------------------------- Other methods -----------------------------*/
    
    /**
     * Returns a random object from a collection of objects.
     * @param    Collection<Object>    the collection
     * @return                         the random object from the collection
     */
    public static Object randomObject(Collection<?> collection) {
    	
    	int finalIndex = RandomUtil.randomIndex(collection);
    	int currentIndex = 0;
    	
    	for(Object obj : collection) {
    		if(currentIndex++ == finalIndex) {
    			return obj;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Returns a random object from a collection of objects.
     * @param    Object[]    the array
     * @return               the random object from the collection
     */
    public static Object randomObject(Object[] array) {
    	if(array.length > 0) {
    	    return array[RandomUtil.randomIndex(array)];
    	} else {
    		return null;
    	}
    }
    
    /**  
     * Returns a random index for the collection.
     * @param    Collection<Object>    the collection
     * @return                         random index into the collection
     */
    public static int randomIndex(Collection<?> collection) {
    	Random random = new Random();
        return random.nextInt(collection.size());
    }
    
    /**
     * Returns a random index for the array.
     * @param    Object[]array    the array
     * @return                    random index into the array
     */
    public static int randomIndex(Object[] array) {
    	Random random = new Random();
        return random.nextInt(array.length - 1) + 1;
    }
    
}
