

/* First created by JCasGen Mon Oct 12 20:41:17 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** The information about the confusion matrix for the given test element.
 * Updated by JCasGen Mon Oct 12 20:48:05 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI6/pi6-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class ConfMatrix extends Metric {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ConfMatrix.class);
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
  protected ConfMatrix() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ConfMatrix(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ConfMatrix(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ConfMatrix(JCas jcas, int begin, int end) {
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
  //* Feature: tp

  /** getter for tp - gets True positives score for the given test element.
   * @generated
   * @return value of the feature 
   */
  public Metric getTp() {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_tp == null)
      jcasType.jcas.throwFeatMissing("tp", "type.ConfMatrix");
    return (Metric)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_tp)));}
    
  /** setter for tp - sets True positives score for the given test element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTp(Metric v) {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_tp == null)
      jcasType.jcas.throwFeatMissing("tp", "type.ConfMatrix");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_tp, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: tn

  /** getter for tn - gets True negatives score for the given test element.
   * @generated
   * @return value of the feature 
   */
  public Metric getTn() {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_tn == null)
      jcasType.jcas.throwFeatMissing("tn", "type.ConfMatrix");
    return (Metric)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_tn)));}
    
  /** setter for tn - sets True negatives score for the given test element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTn(Metric v) {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_tn == null)
      jcasType.jcas.throwFeatMissing("tn", "type.ConfMatrix");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_tn, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: fp

  /** getter for fp - gets False positives score for the given Test Element.
   * @generated
   * @return value of the feature 
   */
  public Metric getFp() {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_fp == null)
      jcasType.jcas.throwFeatMissing("fp", "type.ConfMatrix");
    return (Metric)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_fp)));}
    
  /** setter for fp - sets False positives score for the given Test Element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFp(Metric v) {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_fp == null)
      jcasType.jcas.throwFeatMissing("fp", "type.ConfMatrix");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_fp, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: fn

  /** getter for fn - gets False negatives score for the given test element.
   * @generated
   * @return value of the feature 
   */
  public Metric getFn() {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_fn == null)
      jcasType.jcas.throwFeatMissing("fn", "type.ConfMatrix");
    return (Metric)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_fn)));}
    
  /** setter for fn - sets False negatives score for the given test element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFn(Metric v) {
    if (ConfMatrix_Type.featOkTst && ((ConfMatrix_Type)jcasType).casFeat_fn == null)
      jcasType.jcas.throwFeatMissing("fn", "type.ConfMatrix");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConfMatrix_Type)jcasType).casFeatCode_fn, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    