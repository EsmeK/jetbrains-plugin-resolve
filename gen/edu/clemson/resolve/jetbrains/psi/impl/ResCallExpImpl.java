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

public class ResCallExpImpl extends ResExpImpl implements ResCallExp {

  public ResCallExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitCallExp(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ResArgumentList getArgumentList() {
    return findNotNullChildByClass(ResArgumentList.class);
  }

  @Override
  @Nullable
  public ResReferenceExp getReferenceExp() {
    return findChildByClass(ResReferenceExp.class);
  }

}
