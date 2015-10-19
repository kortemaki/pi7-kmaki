
/* First created by JCasGen Mon Oct 12 20:41:17 EDT 2015 */
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

/** The information about the confusion matrix for the given test element.
 * Updated by JCasGen Mon Oct 12 20:48:05 EDT 2015
 * @generated */
public class ConfMatrix_Type extends Metric_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ConfMatrix_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ConfMatrix_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ConfMatrix(addr, ConfMatrix_Type.this);
  			   ConfMatrix_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ConfMatrix(addr, ConfMatrix_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ConfMatrix.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.ConfMatrix");
 
  /** @generated */
  final Feature casFeat_tp;
  /** @generated */
  final int     casFeatCode_tp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTp(int addr) {
        if (featOkTst && casFeat_tp == null)
      jcas.throwFeatMissing("tp", "type.ConfMatrix");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTp(int addr, int v) {
        if (featOkTst && casFeat_tp == null)
      jcas.throwFeatMissing("tp", "type.ConfMatrix");
    ll_cas.ll_setRefValue(addr, casFeatCode_tp, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tn;
  /** @generated */
  final int     casFeatCode_tn;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTn(int addr) {
        if (featOkTst && casFeat_tn == null)
      jcas.throwFeatMissing("tn", "type.ConfMatrix");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tn);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTn(int addr, int v) {
        if (featOkTst && casFeat_tn == null)
      jcas.throwFeatMissing("tn", "type.ConfMatrix");
    ll_cas.ll_setRefValue(addr, casFeatCode_tn, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fp;
  /** @generated */
  final int     casFeatCode_fp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFp(int addr) {
        if (featOkTst && casFeat_fp == null)
      jcas.throwFeatMissing("fp", "type.ConfMatrix");
    return ll_cas.ll_getRefValue(addr, casFeatCode_fp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFp(int addr, int v) {
        if (featOkTst && casFeat_fp == null)
      jcas.throwFeatMissing("fp", "type.ConfMatrix");
    ll_cas.ll_setRefValue(addr, casFeatCode_fp, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fn;
  /** @generated */
  final int     casFeatCode_fn;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFn(int addr) {
        if (featOkTst && casFeat_fn == null)
      jcas.throwFeatMissing("fn", "type.ConfMatrix");
    return ll_cas.ll_getRefValue(addr, casFeatCode_fn);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFn(int addr, int v) {
        if (featOkTst && casFeat_fn == null)
      jcas.throwFeatMissing("fn", "type.ConfMatrix");
    ll_cas.ll_setRefValue(addr, casFeatCode_fn, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ConfMatrix_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tp = jcas.getRequiredFeatureDE(casType, "tp", "type.Metric", featOkTst);
    casFeatCode_tp  = (null == casFeat_tp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tp).getCode();

 
    casFeat_tn = jcas.getRequiredFeatureDE(casType, "tn", "type.Metric", featOkTst);
    casFeatCode_tn  = (null == casFeat_tn) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tn).getCode();

 
    casFeat_fp = jcas.getRequiredFeatureDE(casType, "fp", "type.Metric", featOkTst);
    casFeatCode_fp  = (null == casFeat_fp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fp).getCode();

 
    casFeat_fn = jcas.getRequiredFeatureDE(casType, "fn", "type.Metric", featOkTst);
    casFeatCode_fn  = (null == casFeat_fn) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fn).getCode();

  }
}



    