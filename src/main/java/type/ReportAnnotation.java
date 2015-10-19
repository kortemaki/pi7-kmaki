

/* First created by JCasGen Mon Oct 12 20:48:05 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;


/** An annotation which summarizes the system's performance with respect to a test element.
 * Updated by JCasGen Mon Oct 12 20:48:05 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI6/pi6-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class ReportAnnotation extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ReportAnnotation.class);
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
  protected ReportAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ReportAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ReportAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ReportAnnotation(JCas jcas, int begin, int end) {
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

  /** getter for testElement - gets The test element referred to by this report.
   * @generated
   * @return value of the feature 
   */
  public TestElementAnnotation getTestElement() {
    if (ReportAnnotation_Type.featOkTst && ((ReportAnnotation_Type)jcasType).casFeat_testElement == null)
      jcasType.jcas.throwFeatMissing("testElement", "type.ReportAnnotation");
    return (TestElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ReportAnnotation_Type)jcasType).casFeatCode_testElement)));}
    
  /** setter for testElement - sets The test element referred to by this report. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTestElement(TestElementAnnotation v) {
    if (ReportAnnotation_Type.featOkTst && ((ReportAnnotation_Type)jcasType).casFeat_testElement == null)
      jcasType.jcas.throwFeatMissing("testElement", "type.ReportAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ReportAnnotation_Type)jcasType).casFeatCode_testElement, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: metrics

  /** getter for metrics - gets The metrics associated with the given report.
   * @generated
   * @return value of the feature 
   */
  public FSList getMetrics() {
    if (ReportAnnotation_Type.featOkTst && ((ReportAnnotation_Type)jcasType).casFeat_metrics == null)
      jcasType.jcas.throwFeatMissing("metrics", "type.ReportAnnotation");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ReportAnnotation_Type)jcasType).casFeatCode_metrics)));}
    
  /** setter for metrics - sets The metrics associated with the given report. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetrics(FSList v) {
    if (ReportAnnotation_Type.featOkTst && ((ReportAnnotation_Type)jcasType).casFeat_metrics == null)
      jcasType.jcas.throwFeatMissing("metrics", "type.ReportAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ReportAnnotation_Type)jcasType).casFeatCode_metrics, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    