package edu.clemson.resolve.jetbrains;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import edu.clemson.resolve.jetbrains.parser.ResolveLexer;
import edu.clemson.resolve.jetbrains.parser.ResolveParser;
import edu.clemson.resolve.jetbrains.psi.ResFile;
import org.antlr.jetbrains.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.jetbrains.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.jetbrains.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.jetbrains.adaptor.psi.ANTLRPsiNodeAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;

/** The implementation of the RESOLVE language parser. Defines methods for
 *  creating an instance of our lexer and parser via
 *  {@link #createLexer(Project)} and {@link #createParser(Project)},
 *  respectively.
 *
 *  @since 0.0.1
 *  @see LanguageParserDefinitions#forLanguage(Language)
 */
public class RESOLVEParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE =
            new IFileElementType(RESOLVELanguage.INSTANCE);

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(
                RESOLVELanguage.INSTANCE,
                ResolveParser.tokenNames,
                ResolveParser.ruleNames);
    }

    public static final TokenSet COMMENTS =
            PSIElementTypeFactory.createTokenSet(
                    RESOLVELanguage.INSTANCE,
                    ResolveLexer.COMMENT,
                    ResolveLexer.LINE_COMMENT);

    public static final TokenSet WHITESPACE =
            PSIElementTypeFactory.createTokenSet(
                    RESOLVELanguage.INSTANCE,
                    ResolveLexer.WS);

    public static final TokenSet STRING =
            PSIElementTypeFactory.createTokenSet(
                    RESOLVELanguage.INSTANCE,
                    ResolveLexer.STRING);

    //public static final TokenSet PARAMETER_MODES = TokenSet.create(ALTERS,
    //        UPDATES, CLEARS, RESTORES, PRESERVES, REPLACES, EVALUATES);

    @NotNull @Override public Lexer createLexer(Project project) {
        ResolveLexer lexer = new ResolveLexer(null);
        return new ANTLRLexerAdaptor(RESOLVELanguage.INSTANCE, lexer);
    }


    @NotNull public PsiParser createParser(final Project project) {
        final ResolveParser parser = new ResolveParser(null);
        return new ANTLRParserAdaptor(RESOLVELanguage.INSTANCE, parser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                return ((ResolveParser)parser).moduleDecl();
            }
        };
    }

    /** "Tokens of those types are automatically skipped by PsiBuilder." This
     *  apparently applies to this method, {@link #getCommentTokens()}, and
     *  {@link #getStringLiteralElements()}.
     */
    @NotNull @Override public TokenSet getWhitespaceTokens() {
        return WHITESPACE;
    }

    @NotNull @Override public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull @Override public TokenSet getStringLiteralElements() {
        return STRING;
    }

    /** What is the IFileElementType of the root parse tree node? It
     *  is called from {@link #createFile(FileViewProvider)} at least.
     */
    @NotNull @Override public IFileElementType getFileNodeType() {
        return FILE;
    }

    /** Convert from *internal* parse node (AST they call it) to final PSI node.
     *  This converts only internal rule nodes apparently, not leaf nodes.
     *  Leaves are just tokens I guess.
     *
     *  If you don't care to distinguish PSI nodes by type, it is sufficient
     *  to create a {@link ASTWrapperPsiElement} around the parse tree node
     *  ({@link ASTNode} in jetbrains speak).
     */
    @NotNull @Override public PsiElement createElement(ASTNode node) {
        return new ANTLRPsiNodeAdaptor(node);
    }

    /** Create the root of your PSI tree (a {@link PsiFile}).
     *
     *  From IntelliJ IDEA Architectural Overview:
     *  "A PSI (Program Structure Interface) file is the root of a structure
     *  representing the contents of a file as a hierarchy of elements
     *  in a particular programming language."
     *
     *  Psi based File is to be distinguished from a
     *  {@link com.intellij.lang.FileASTNode}, which is a parse
     *  tree node that eventually becomes a {@link PsiFile}. From this, we can get
     *  it back via: {@link PsiFile#getNode}.
     */
    @Override public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new ResFile(fileViewProvider);
    }

    @Override public SpaceRequirements spaceExistanceTypeBetweenTokens(
            ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
