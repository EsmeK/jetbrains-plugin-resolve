package edu.clemson.resolve.jetbrains.editor;

import com.intellij.codeInsight.folding.CodeFoldingSettings;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.folding.NamedFoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import edu.clemson.resolve.jetbrains.RESOLVEFileType;
import edu.clemson.resolve.jetbrains.RESOLVEParserDefinition;
import edu.clemson.resolve.jetbrains.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * Created by Esme on 2/3/2016.
 */
public class RESOLVEFoldingBuilder extends CustomFoldingBuilder implements DumbAware {

    private static void foldTypes(@Nullable PsiElement e, @NotNull List<FoldingDescriptor> result) {
        if (e instanceof ResOperationProcedureDecl) {
            //if (((ResOperationProcedureDecl)e).getParamDeclList().isEmpty()) return;
            fold(e, ((ResOperationProcedureDecl)e).getProcedure(), ((ResOperationProcedureDecl)e).getEnd(), "{...}", result);
        }
    }

    private static void fold(@NotNull PsiElement e,
                             @Nullable PsiElement l,
                             @Nullable PsiElement r,
                             @NotNull String placeholderText,
                             @NotNull List<FoldingDescriptor> result) {
        if (l != null && r != null) {
            result.add(new NamedFoldingDescriptor(e, l.getTextRange().getStartOffset(), r.getTextRange().getEndOffset(), null, placeholderText));
        }
    }

    // com.intellij.codeInsight.folding.impl.JavaFoldingBuilderBase.addCodeBlockFolds()
    private static void addCommentFolds(@NotNull PsiElement comment,
                                        @NotNull Set<PsiElement> processedComments,
                                        @NotNull List<FoldingDescriptor> result) {
        if (processedComments.contains(comment)) return;

        PsiElement end = null;
        for (PsiElement current = comment.getNextSibling(); current != null; current = current.getNextSibling()) {
            ASTNode node = current.getNode();
            if (node == null) break;
            IElementType elementType = node.getElementType();
            if (elementType == RESOLVEParserDefinition.LINE_COMMENT) {
                end = current;
                processedComments.add(current);
                continue;
            }
            if (elementType == TokenType.WHITE_SPACE) continue;
            break;
        }

        if (end != null) {
            int startOffset = comment.getTextRange().getStartOffset();
            int endOffset = end.getTextRange().getEndOffset();
            result.add(new NamedFoldingDescriptor(comment, startOffset, endOffset, null, "/.../"));
        }
    }

    @Override
    protected void buildLanguageFoldRegions(@NotNull final List<FoldingDescriptor> result,
                                            @NotNull PsiElement root,
                                            @NotNull Document document,
                                            boolean quick) {
        if (!(root instanceof ResFile)) return;
        ResFile file = (ResFile)root;
        if (!file.isContentsLoaded()) return;

        for (ResOperationProcedureDecl type : PsiTreeUtil.findChildrenOfType(file, ResOperationProcedureDecl.class)) {
            fold(type, type.getProcedure(), type.getCloseIdentifier(), "{...}", result);
        }

        for (ResIfStatement type : PsiTreeUtil.findChildrenOfType(file, ResIfStatement.class)) {
            fold(type, type.getThen(), type.getEnd(), "...", result);
        }

        for (ResWhileStatement type : PsiTreeUtil.findChildrenOfType(file, ResWhileStatement.class)) {
            fold(type, type.getDo(), type.getEnd(), "...", result);
        }

        if (!quick) {
            final Set<PsiElement> processedComments = ContainerUtil.newHashSet();
            PsiTreeUtil.processElements(file, new PsiElementProcessor() {
                @Override
                public boolean execute(@NotNull PsiElement element) {
                    ASTNode node = element.getNode();
                    IElementType type = node.getElementType();
                    TextRange range = element.getTextRange();
                    if (type == RESOLVEParserDefinition.MULTILINE_COMMENT && range.getLength() > 2) {
                        result.add(new NamedFoldingDescriptor(node, range, null, "/*...*/"));
                    }

                    if (type == RESOLVEParserDefinition.LINE_COMMENT) {
                        addCommentFolds(element, processedComments, result);
                    }
                    foldTypes(element, result); // folding for inner types
                    return true;
                }
            });
        }
    }

    @Nullable
    @Override
    protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
        return "...";
    }

    @Override
    protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
        IElementType type = node.getElementType();
        if (type == RESOLVEParserDefinition.LINE_COMMENT || type == RESOLVEParserDefinition.MULTILINE_COMMENT) {
            return CodeFoldingSettings.getInstance().COLLAPSE_DOC_COMMENTS;
        }
        return CodeFoldingSettings.getInstance().COLLAPSE_IMPORTS;// && node.getElementType() == GoTypes.IMPORT_LIST;
    }
}
