// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ResChangingClause extends ResCompositeElement {

  @NotNull
  List<ResExp> getExpList();

  @NotNull
  PsiElement getChanging();

  @Nullable
  PsiElement getSemicolon();

}
