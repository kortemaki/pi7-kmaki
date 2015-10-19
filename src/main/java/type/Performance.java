

/* First created by JCasGen Mon Oct 05 10:45:47 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.cas.FSList;


/** Annotates performance of the system on a single test element.
 * Updated by JCasGen Mon Oct 12 20:41:17 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI6/pi6-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Performance extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Performance.class);
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
  protected Performance() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Performance(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Performance(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Performance(JCas jcas, int begin, int end) {
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
  //* Feature: testElement

  /** getter for testElement - gets The test element annotated with this performance.
   * @generated
   * @return value of the feature 
   */
  public TestElementAnnotation getTestElement() {
    if (Performance_Type.featOkTst && ((Performance_Type)jcasType).casFeat_testElement == null)
      jcasType.jcas.throwFeatMissing("testElement", "type.Performance");
    return (TestElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Performance_Type)jcasType).casFeatCode_testElement)));}
    
  /** setter for testElement - sets The test element annotated with this performance. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTestElement(TestElementAnnotation v) {
    if (Performance_Type.featOkTst && ((Performance_Type)jcasType).casFeat_testElement == null)
      jcasType.jcas.throwFeatMissing("testElement", "type.Performance");
    jcasType.ll_cas.ll_setRefValue(addr, ((Performance_Type)jcasType).casFeatCode_testElement, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: metrics

  /** getter for metrics - gets The metrics annotated for the test element.
   * @generated
   * @return value of the feature 
   */
  public FSList getMetrics() {
    if (Performance_Type.featOkTst && ((Performance_Type)jcasType).casFeat_metrics == null)
      jcasType.jcas.throwFeatMissing("metrics", "type.Performance");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Performance_Type)jcasType).casFeatCode_metrics)));}
    
  /** setter for metrics - sets The metrics annotated for the test element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetrics(FSList v) {
    if (Performance_Type.featOkTst && ((Performance_Type)jcasType).casFeat_metrics == null)
      jcasType.jcas.throwFeatMissing("metrics", "type.Performance");
    jcasType.ll_cas.ll_setRefValue(addr, ((Performance_Type)jcasType).casFeatCode_metrics, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    