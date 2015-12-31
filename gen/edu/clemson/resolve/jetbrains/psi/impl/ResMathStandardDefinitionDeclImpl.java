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

public class ResMathStandardDefinitionDeclImpl extends ResAbstractMathDefinitionImpl implements ResMathStandardDefinitionDecl {

  public ResMathStandardDefinitionDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ResVisitor) ((ResVisitor)visitor).visitMathStandardDefinitionDecl(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ResMathExp getMathExp() {
    return findChildByClass(ResMathExp.class);
  }

  @Override
  @Nullable
  public ResMathInfixDefinitionSignature getMathInfixDefinitionSignature() {
    return findChildByClass(ResMathInfixDefinitionSignature.class);
  }

  @Override
  @Nullable
  public ResMathOutfixDefinitionSignature getMathOutfixDefinitionSignature() {
    return findChildByClass(ResMathOutfixDefinitionSignature.class);
  }

  @Override
  @Nullable
  public ResMathPrefixDefinitionSignature getMathPrefixDefinitionSignature() {
    return findChildByClass(ResMathPrefixDefinitionSignature.class);
  }

  @Override
  @NotNull
  public PsiElement getDefinition() {
    return findNotNullChildByType(DEFINITION);
  }

  @Override
  @Nullable
  public PsiElement getImplicit() {
    return findChildByType(IMPLICIT);
  }

  @Override
  @Nullable
  public PsiElement getIs() {
    return findChildByType(IS);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
