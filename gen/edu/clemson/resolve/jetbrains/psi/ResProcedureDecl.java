// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ResProcedureDecl extends ResCompositeElement {

  @Nullable
  ResCloseIdentifier getCloseIdentifier();

  @Nullable
  ResOpBlock getOpBlock();

  @NotNull
  List<ResParamDecl> getParamDeclList();

  @Nullable
  ResType getType();

  @Nullable
  PsiElement getColon();

  @Nullable
  PsiElement getEnd();

  @Nullable
  PsiElement getLparen();

  @NotNull
  PsiElement getProcedure();

  @Nullable
  PsiElement getRecursive();

  @Nullable
  PsiElement getRparen();

  @NotNull
  PsiElement getIdentifier();

}
