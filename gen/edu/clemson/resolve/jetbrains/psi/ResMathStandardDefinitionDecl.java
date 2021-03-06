// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ResMathStandardDefinitionDecl extends ResMathDefinitionDecl {

  @Nullable
  ResMathExp getMathExp();

  @Nullable
  ResMathInfixDefinitionSignature getMathInfixDefinitionSignature();

  @Nullable
  ResMathOutfixDefinitionSignature getMathOutfixDefinitionSignature();

  @Nullable
  ResMathPrefixDefinitionSignature getMathPrefixDefinitionSignature();

  @NotNull
  PsiElement getDefinition();

  @Nullable
  PsiElement getImplicit();

  @Nullable
  PsiElement getIs();

  @Nullable
  PsiElement getSemicolon();

}
