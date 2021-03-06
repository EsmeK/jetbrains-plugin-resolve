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

public class ResFacilityModuleDeclImpl extends ResAbstractModuleImpl implements ResFacilityModuleDecl {

  public ResFacilityModuleDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitFacilityModuleDecl(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ResFacilityBlock getFacilityBlock() {
    return findChildByClass(ResFacilityBlock.class);
  }

  @Override
  @Nullable
  public ResRequiresClause getRequiresClause() {
    return findChildByClass(ResRequiresClause.class);
  }

  @Override
  @Nullable
  public ResUsesItemList getUsesItemList() {
    return findChildByClass(ResUsesItemList.class);
  }

  @Override
  @Nullable
  public PsiElement getEnd() {
    return findChildByType(END);
  }

  @Override
  @NotNull
  public PsiElement getFacility() {
    return findNotNullChildByType(FACILITY);
  }

}
