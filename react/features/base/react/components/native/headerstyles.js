// @flex

import { StyleSheet } from 'react-native';

import { ColorSchemeRegistry, schemeColor } from '../../../color-scheme';
import { BoxModel } from '../../../styles';

const HEADER_FONT_SIZE = 18;
const HEADER_HEIGHT = 48;
const HEADER_PADDING = BoxModel.padding / 2;

ColorSchemeRegistry.register('Header', {

    /**
     * Style of a disabled button in the header (e.g. Next).
     */
    disabledButtonText: {
        opacity: 0.6
    },

    /**
     * Platform specific header button (e.g. back, menu, etc).
     */
    headerButtonIcon: {
        alignSelf: 'center',
        color: 'white',
        fontSize: 22,
        marginRight: 12,
        padding: 8
    },

    headerButtonText: {
        color: 'white',
        fontSize: HEADER_FONT_SIZE
    },

    /**
     * Style of the header overlay to cover the unsafe areas.
     */
    headerOverlay: {
        backgroundColor: 'black'
    },

    /**
     * Generic style for a label placed in the header.
     */
    headerText: {
        color: 'white',
        fontSize: HEADER_FONT_SIZE
    },

    headerTextWrapper: {
        alignItems: 'center',
        justifyContent: 'center',
        left: 0,
        position: 'absolute',
        right: 0
    },

    /**
     * The top-level element of a page.
     */
    page: {
        ...StyleSheet.absoluteFillObject,
        alignItems: 'stretch',
        flex: 1,
        flexDirection: 'column',
        overflow: 'hidden'
    },

    /**
     * Base style of Header.
     */
    screenHeader: {
        alignItems: 'center',
        backgroundColor: '#040404',
        color: 'white',
        flexDirection: 'row',
        height: HEADER_HEIGHT,
        justifyContent: 'space-between',
        paddingHorizontal: BoxModel.padding,
        paddingVertical: HEADER_PADDING
    },

    statusBar: '#040404',

    statusBarContent: schemeColor('statusBarContent')
});
