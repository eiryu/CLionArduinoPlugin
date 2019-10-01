package com.vladsch.clionarduinoplugin.generators.cmake;

import com.vladsch.flexmark.spec.IParseBase;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import com.vladsch.flexmark.util.ast.IParse;
import com.vladsch.flexmark.util.ast.IRender;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMakeParserSpecTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/cmake_parser_spec.md";
    public static final String SPEC_FILE_URL = "/Users/vlad/src/projects/CLionArduinoPlugin/test-resources" + SPEC_RESOURCE;

    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(CMakeParser.AUTO_CONFIG, false);

    protected static final Map<String, DataHolder> optionsMap = new HashMap<String, DataHolder>();
    static {
        optionsMap.put("auto-config", new MutableDataSet().set(CMakeParser.AUTO_CONFIG, true));
        optionsMap.put("bracket-comments", new MutableDataSet().set(CMakeParser.BRACKET_COMMENTS, true));
        optionsMap.put("line-cont", new MutableDataSet().set(CMakeParser.LINE_CONTINUATION, true));

        optionsMap.put("ast-line-eol", new MutableDataSet().set(CMakeParser.AST_LINE_END_EOL, true));
        optionsMap.put("ast-comments", new MutableDataSet().set(CMakeParser.AST_COMMENTS, true));
        optionsMap.put("ast-blank", new MutableDataSet().set(CMakeParser.AST_BLANK_LINES, true));
        optionsMap.put("ast-arg-seps", new MutableDataSet().set(CMakeParser.AST_ARGUMENT_SEPARATORS, true));
        optionsMap.put("commented-out", new MutableDataSet().set(CMakeParser.AST_COMMENTED_OUT_COMMANDS, true));

        optionsMap.put("dump-options", new MutableDataSet().set(ExtraRenderer.DUMP_OPTIONS, true));
    }

    private static final IParseBase PARSER = new CMakeIParser();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final IRender RENDERER = new ExtraRenderer();

    private static DataHolder optionsSet(String optionSet) {
        return optionsMap.get(optionSet);
    }

    public CMakeParserSpecTest(SpecExample example) {
        super(example.withFileUrl(SPEC_FILE_URL));
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE, SPEC_FILE_URL);
    }

    @Override
    public DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    public String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    public IParse parser() {
        return PARSER;
    }

    @Override
    public IRender renderer() {
        return RENDERER;
    }
}
