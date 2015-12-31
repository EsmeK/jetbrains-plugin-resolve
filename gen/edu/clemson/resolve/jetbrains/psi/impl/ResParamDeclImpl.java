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

public class ResParamDeclImpl extends ResCompositeElementImpl implements ResParamDecl {

  public ResParamDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitParamDecl(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ResParamDef> getParamDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResParamDef.class);
  }

  @Override
  @NotNull
  public ResParameterMode getParameterMode() {
    return findNotNullChildByClass(ResParameterMode.class);
  }

  @Override
  @Nullable
  public ResType getType() {
    return findChildByClass(ResType.class);
  }

  @Override
  @Nullable
  public PsiElement getColon() {
    return findChildByType(COLON);
  }

}
