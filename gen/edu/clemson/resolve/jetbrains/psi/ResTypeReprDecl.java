// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;

public interface ResTypeReprDecl extends ResTypeLikeNodeDecl {

  @Nullable
  ResConventionsClause getConventionsClause();

  @Nullable
  ResCorrespondenceClause getCorrespondenceClause();

  @Nullable
  ResType getType();

  @Nullable
  ResTypeImplInit getTypeImplInit();

  @Nullable
  PsiElement getEquals();

  @NotNull
  PsiElement getFamilyType();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getIdentifier();

  @Nullable
  ResType getResTypeInner(ResolveState context);

}
