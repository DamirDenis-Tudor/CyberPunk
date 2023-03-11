package Components.StaticComponents;

import GameWindow.GameWindow;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class GameMap implements StaticComponent {
    private static final int tileDimension = 36;
    private static final int tileScale = 1;
    private Map<String, Sprite> tiles; // String - id , Tile - object

    private List<Sprite> background; //
    private String[][] indexMap; // indexes map
    private int width; // lines
    private int height; // columns

    public GameMap(String path) {
        try {
            /*
                first initialize the document element
             */
            DocumentBuilder builder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();

            Document document = builder.parse(new File(path));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();

            Element source = (Element) root.getElementsByTagName("tileset").item(0);

            /*
              load the tiles
             */
            tiles = new HashMap<>();

            Document tilesDocument = builder.parse(new File("src/ResourcesFiles/" + source.getAttribute("source")));
            document.getDocumentElement().normalize();

            Element tilesRoot = tilesDocument.getDocumentElement();

            NodeList elements = tilesRoot.getElementsByTagName("tile");

            for (int index = 0; index < elements.getLength(); ++index) {
                Element tileElement = (Element) elements.item(index);
                Element imageElement = (Element) tileElement.getFirstChild().getNextSibling();

                tiles.put(
                        Integer.toString(Integer.parseInt(tileElement.getAttribute("id")) + 1),
                        new Sprite("src/ResourcesFiles/" + imageElement.getAttribute("source"))
                );
            }

            /*
             * load the background
             */


            /*
             * load the matrix
             */

            Element layer = (Element) root.getElementsByTagName("layer").item(0);
            height = Integer.parseInt(layer.getAttribute("height"));
            width = Integer.parseInt(layer.getAttribute("width"));

            String buffer = root.getElementsByTagName("data").item(0).
                    getFirstChild().getTextContent();

            String[] rows = buffer.split("\n"); // split the string into rows

            height = rows.length - 1;
            width = rows[1].split(",").length;

            indexMap = new String[height][width];

            for (int i = 0; i < height; i++) {
                String[] cols = rows[i + 1].split(",");
                System.arraycopy(cols, 0, indexMap[i], 0, width);
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; j++) {
                if (!Objects.equals(indexMap[i][j], "0")) {
                    GameWindow.getInstance().
                            getGraphics().
                            drawImage(
                                    tiles.get(indexMap[i][j]).getTile(),
                                    j * tileDimension * tileScale,
                                    i * tileDimension * tileScale,
                                    tileDimension * tileScale,
                                    tileDimension * tileScale,
                                    null);
                }
            }
        }
    }
}