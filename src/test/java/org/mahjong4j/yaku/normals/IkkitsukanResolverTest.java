package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class IkkitsukanResolverTest {
    private IkkitsukanResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meld = new ArrayList<>(5);
        meld.add(new Pair(W3));
        meld.add(new Sequence(true, T4));
        meld.add(new Sequence(false, D2));
        meld.add(new Sequence(false, D8));
        meld.add(new Sequence(true, D5));
        MeldDirectory comp = new MeldDirectory(meld, D3);
        resolver = new IkkitsukanResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.IKKITSUKAN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}