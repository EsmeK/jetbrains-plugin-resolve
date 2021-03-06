// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static edu.clemson.resolve.jetbrains.ResTypes.*;
import edu.clemson.resolve.jetbrains.psi.*;

public class ResExtensionPairingImpl extends ResCompositeElementImpl implements ResExtensionPairing {

  public ResExtensionPairingImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitExtensionPairing(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ResModuleArgList> getModuleArgListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResModuleArgList.class);
  }

  @Override
  @NotNull
  public List<ResModuleSpec> getModuleSpecList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResModuleSpec.class);
  }

  @Override
  @NotNull
  public PsiElement getExtended() {
    return findNotNullChildByType(EXTENDED);
  }

  @Override
  @Nullable
  public PsiElement getExternally() {
    return findChildByType(EXTERNALLY);
  }

  @Override
  @Nullable
  public PsiElement getImplemented() {
    return findChildByType(IMPLEMENTED);
  }

}
