// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ResMathLambdaExp extends ResMathExp {

  @Nullable
  ResMathExp getMathExp();

  @NotNull
  List<ResMathVarDeclGroup> getMathVarDeclGroupList();

  @Nullable
  PsiElement getDot();

  @NotNull
  PsiElement getLambda();

}
