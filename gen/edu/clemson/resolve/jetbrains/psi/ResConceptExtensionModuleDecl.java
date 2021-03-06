// This is a generated file. Not intended for manual editing.
package edu.clemson.resolve.jetbrains.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ResConceptExtensionModuleDecl extends ResModuleDecl {

  @Nullable
  ResConceptBlock getConceptBlock();

  @Nullable
  ResModuleSpec getModuleSpec();

  @Nullable
  ResRequiresClause getRequiresClause();

  @Nullable
  ResSpecModuleParameters getSpecModuleParameters();

  @Nullable
  ResUsesItemList getUsesItemList();

  @NotNull
  PsiElement getConcept();

  @Nullable
  PsiElement getEnd();

  @NotNull
  PsiElement getExtension();

  @Nullable
  PsiElement getFor();

}
