/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iserver.services.ws.search;

public class SearchCondition  extends com.strandgenomics.imaging.iserver.services.ws.search.SearchField  implements java.io.Serializable {
    private java.lang.Object lowerLimit;

    private java.lang.Object upperLimit;

    public SearchCondition() {
    }

    public SearchCondition(
           java.lang.String name,
           int type,
           java.lang.Object lowerLimit,
           java.lang.Object upperLimit) {
        super(
            name,
            type);
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }


    /**
     * Gets the lowerLimit value for this SearchCondition.
     * 
     * @return lowerLimit
     */
    public java.lang.Object getLowerLimit() {
        return lowerLimit;
    }


    /**
     * Sets the lowerLimit value for this SearchCondition.
     * 
     * @param lowerLimit
     */
    public void setLowerLimit(java.lang.Object lowerLimit) {
        this.lowerLimit = lowerLimit;
    }


    /**
     * Gets the upperLimit value for this SearchCondition.
     * 
     * @return upperLimit
     */
    public java.lang.Object getUpperLimit() {
        return upperLimit;
    }


    /**
     * Sets the upperLimit value for this SearchCondition.
     * 
     * @param upperLimit
     */
    public void setUpperLimit(java.lang.Object upperLimit) {
        this.upperLimit = upperLimit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchCondition)) return false;
        SearchCondition other = (SearchCondition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lowerLimit==null && other.getLowerLimit()==null) || 
             (this.lowerLimit!=null &&
              this.lowerLimit.equals(other.getLowerLimit()))) &&
            ((this.upperLimit==null && other.getUpperLimit()==null) || 
             (this.upperLimit!=null &&
              this.upperLimit.equals(other.getUpperLimit())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getLowerLimit() != null) {
            _hashCode += getLowerLimit().hashCode();
        }
        if (getUpperLimit() != null) {
            _hashCode += getUpperLimit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchCondition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:isearch", "SearchCondition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowerLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lowerLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upperLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upperLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
