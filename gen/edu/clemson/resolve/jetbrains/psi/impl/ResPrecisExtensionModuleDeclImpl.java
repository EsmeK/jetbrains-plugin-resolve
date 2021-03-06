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

public class ResPrecisExtensionModuleDeclImpl extends ResAbstractModuleImpl implements ResPrecisExtensionModuleDecl {

  public ResPrecisExtensionModuleDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitPrecisExtensionModuleDecl(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ResModuleSpec> getModuleSpecList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ResModuleSpec.class);
  }

  @Override
  @Nullable
  public ResPrecisBlock getPrecisBlock() {
    return findChildByClass(ResPrecisBlock.class);
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

  @Override
  @NotNull
  public PsiElement getPrecis() {
    return findNotNullChildByType(PRECIS);
  }

  @Override
  @Nullable
  public PsiElement getWith() {
    return findChildByType(WITH);
  }

}
