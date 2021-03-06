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

public class ResConceptExtensionModuleDeclImpl extends ResAbstractModuleImpl implements ResConceptExtensionModuleDecl {

  public ResConceptExtensionModuleDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitConceptExtensionModuleDecl(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ResConceptBlock getConceptBlock() {
    return findChildByClass(ResConceptBlock.class);
  }

  @Override
  @Nullable
  public ResModuleSpec getModuleSpec() {
    return findChildByClass(ResModuleSpec.class);
  }

  @Override
  @Nullable
  public ResRequiresClause getRequiresClause() {
    return findChildByClass(ResRequiresClause.class);
  }

  @Override
  @Nullable
  public ResSpecModuleParameters getSpecModuleParameters() {
    return findChildByClass(ResSpecModuleParameters.class);
  }

  @Override
  @Nullable
  public ResUsesItemList getUsesItemList() {
    return findChildByClass(ResUsesItemList.class);
  }

  @Override
  @NotNull
  public PsiElement getConcept() {
    return findNotNullChildByType(CONCEPT);
  }

  @Override
  @Nullable
  public PsiElement getEnd() {
    return findChildByType(END);
  }

  @Override
  @NotNull
  public PsiElement getExtension() {
    return findNotNullChildByType(EXTENSION);
  }

  @Override
  @Nullable
  public PsiElement getFor() {
    return findChildByType(FOR);
  }

}
