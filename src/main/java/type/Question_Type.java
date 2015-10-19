
/* First created by JCasGen Mon Oct 05 10:08:06 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Stores the information about a question.
 * Updated by JCasGen Mon Oct 19 15:25:59 EDT 2015
 * @generated */
public class Question_Type extends Span_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Question_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Question_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Question(addr, Question_Type.this);
  			   Question_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Question(addr, Question_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Question.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.Question");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_passages;
  /** @generated */
  final int     casFeatCode_passages;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPassages(int addr) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_passages);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassages(int addr, int v) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_passages, v);}
    
  
 
  /** @generated */
  final Feature casFeat_analysisAnnotations;
  /** @generated */
  final int     casFeatCode_analysisAnnotations;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAnalysisAnnotations(int addr) {
        if (featOkTst && casFeat_analysisAnnotations == null)
      jcas.throwFeatMissing("analysisAnnotations", "type.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_analysisAnnotations);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalysisAnnotations(int addr, int v) {
        if (featOkTst && casFeat_analysisAnnotations == null)
      jcas.throwFeatMissing("analysisAnnotations", "type.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_analysisAnnotations, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Question_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_passages = jcas.getRequiredFeatureDE(casType, "passages", "uima.cas.FSList", featOkTst);
    casFeatCode_passages  = (null == casFeat_passages) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_passages).getCode();

 
    casFeat_analysisAnnotations = jcas.getRequiredFeatureDE(casType, "analysisAnnotations", "uima.cas.FSList", featOkTst);
    casFeatCode_analysisAnnotations  = (null == casFeat_analysisAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analysisAnnotations).getCode();

  }
}



    