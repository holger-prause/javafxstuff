package com.playground.javafxstuff.components;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;

/**
 * Accordion which always prevents collapsing the last panel of the accordion.
 * Using expandedPaneProperty to react on expansion 
 * can lead to inconsistencies when the animation is currently running when clicking.
 * For this reason this class registers a mouse event handler on the accordion panes to prevent the mess at all.
 * For me its better not to make a mistake instead of compensating it. 
 * After all i must say this could/should have been build-in functionality(meh)!
 * @author holger prause
 */
public class CustomAccordion extends Accordion {
	public CustomAccordion() {
		// prevent collapsing of the same titled pane
		this.getPanes().addListener((ListChangeListener<TitledPane>) c -> {
			if (c.next()) {
				List<? extends TitledPane> addedSubList = c.getAddedSubList();
				for (TitledPane tp : addedSubList) {
					tp.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
						// check if panel should be kept open in the accordion
						if (tp.isExpanded()) {
							double clickX = event.getScreenX();
							double clickY = event.getScreenY();

							// only consume event(prevent propagation) if click is on the titled panel header directly
							// otherwise - allow clicks on the content area
							Node content = tp.getContent();
							Bounds contentBounds = content.localToScreen(content.getBoundsInLocal());
							if (clickY < contentBounds.getMinY() || clickY > contentBounds.getMaxY()
									|| clickX < contentBounds.getMinX() || clickX > contentBounds.getMaxX()) {
								event.consume();
							}
						}
					});
				}
			}
		});
	}
}
