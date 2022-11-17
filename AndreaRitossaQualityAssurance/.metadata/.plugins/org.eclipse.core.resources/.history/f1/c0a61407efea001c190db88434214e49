package qa.ui.swing;
import javax.swing.*;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class Histogram extends JPanel {
	private static final long serialVersionUID = 1L;

	private Map<String, Double> mappa;

	/** public interface */

	public void showHistogram(Map<String, Double> mappa) {
		this.mappa = mappa;
		repaint();
	}

	/** Internal histogram engine */

	protected void paintComponent(Graphics g) {
		if (mappa == null)
			return; // No display if count is null
		super.paintComponent(g);

		// Find the panel size and bar width and interval dynamically
		int width = getWidth();
		int height = getHeight()/2;
		int interval = (width - 40) / mappa.size();
		int individualWidth = (width - 40) / 24;

		// Find the maximum count. The maximum count has the highest bar
		double maxCount = Collections.max(mappa.values());

		// x is the start position for the first bar in the histogram
		int x = 30;

		// Draw a horizontal base line
		g.drawLine(10, height - 45, width - 10, height - 45);

		for (String key: mappa.keySet()) {
			// Find the bar height
			int barHeight = (int) ( mappa.get(key) / maxCount * (height - 55));
			// Display a bar (i.e. rectangle)
			g.setColor(Color.red);
			g.fillRect(x, height - 45 - barHeight, individualWidth, barHeight);
			// Display a letter under the base line
			g.setColor(Color.black);
			g.drawString(key, x - 10, height - 30);
			// Move x for displaying the next character
			x += interval;
		}
	}

	/** Override getPreferredSize */
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

}
