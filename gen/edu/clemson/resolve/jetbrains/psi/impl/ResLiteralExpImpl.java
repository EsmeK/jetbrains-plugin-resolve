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

public class ResLiteralExpImpl extends ResExpImpl implements ResLiteralExp {

  public ResLiteralExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitLiteralExp(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ResBooleanLiteral getBooleanLiteral() {
    return findChildByClass(ResBooleanLiteral.class);
  }

  @Override
  @Nullable
  public ResStringLiteral getStringLiteral() {
    return findChildByClass(ResStringLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getInt() {
    return findChildByType(INT);
  }

}
