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
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class ResImplBlockImpl extends ResCompositeElementImpl implements ResImplBlock {

  public ResImplBlockImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitImplBlock(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ResFacilityDecl> getFacilityDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResFacilityDecl.class);
  }

  @Override
  @NotNull
  public List<ResMathStandardDefinitionDecl> getMathStandardDefinitionDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResMathStandardDefinitionDecl.class);
  }

  @Override
  @NotNull
  public List<ResOperationProcedureDecl> getOperationProcedureDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResOperationProcedureDecl.class);
  }

  @Override
  @NotNull
  public List<ResProcedureDecl> getProcedureDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResProcedureDecl.class);
  }

  @Override
  @NotNull
  public List<ResTypeReprDecl> getTypeReprDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResTypeReprDecl.class);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return ResPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
