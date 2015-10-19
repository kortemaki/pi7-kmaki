

/* First created by JCasGen Mon Oct 12 11:48:17 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Annotation indicating the performance of the system on some test element under a certain metric.
 * Updated by JCasGen Mon Oct 12 20:48:05 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI6/pi6-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Metric extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Metric.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Metric() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Metric(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Metric(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Metric(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets The value of the system evaluated on the current test element under the metric.
   * @generated
   * @return value of the feature 
   */
  public float getValue() {
    if (Metric_Type.featOkTst && ((Metric_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "type.Metric");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Metric_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets The value of the system evaluated on the current test element under the metric. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(float v) {
    if (Metric_Type.featOkTst && ((Metric_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "type.Metric");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Metric_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: metricName

  /** getter for metricName - gets String naming the metric used to evaluate the system on the test element.
   * @generated
   * @return value of the feature 
   */
  public String getMetricName() {
    if (Metric_Type.featOkTst && ((Metric_Type)jcasType).casFeat_metricName == null)
      jcasType.jcas.throwFeatMissing("metricName", "type.Metric");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Metric_Type)jcasType).casFeatCode_metricName);}
    
  /** setter for metricName - sets String naming the metric used to evaluate the system on the test element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetricName(String v) {
    if (Metric_Type.featOkTst && ((Metric_Type)jcasType).casFeat_metricName == null)
      jcasType.jcas.throwFeatMissing("metricName", "type.Metric");
    jcasType.ll_cas.ll_setStringValue(addr, ((Metric_Type)jcasType).casFeatCode_metricName, v);}    
  }

    