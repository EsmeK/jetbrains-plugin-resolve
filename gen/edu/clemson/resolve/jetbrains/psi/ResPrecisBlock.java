// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface ResPrecisBlock extends ResBlock {

  @NotNull
  List<ResMathCategoricalDefinitionDecl> getMathCategoricalDefinitionDeclList();

  @NotNull
  List<ResMathInductiveDefinitionDecl> getMathInductiveDefinitionDeclList();

  @NotNull
  List<ResMathStandardDefinitionDecl> getMathStandardDefinitionDeclList();

  @NotNull
  List<ResMathTheoremDecl> getMathTheoremDeclList();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
