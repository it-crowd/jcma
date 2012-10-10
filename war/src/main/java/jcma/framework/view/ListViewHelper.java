package jcma.framework.view;

import jcma.framework.business.EntityHome;
import jcma.web.BundleKeys;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.seam.international.status.Messages;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ListViewHelper {
// ------------------------------ FIELDS ------------------------------

    public static final String OUTCOME_FAILURE = "OUTCOME_FAILURE";

    public static final String OUTCOME_NO_ELEMENT_SELECTED = "noElementSelected";

    public static final String OUTCOME_SUCCESS = "OUTCOME_SUCCESS";

// -------------------------- STATIC METHODS --------------------------

    public static <T> String removeSelectedElements(Map<T, Boolean> selection, EntityHome<T> dao, Messages messages)
    {
        Set<T> elementsToRemove = new HashSet<T>();
        for (Map.Entry<T, Boolean> entry : selection.entrySet()) {
            if (entry.getValue()) {
                elementsToRemove.add(entry.getKey());
            }
        }
        if (elementsToRemove.isEmpty()) {
            messages.warn(BundleKeys.NO_ELEMENT_SELECTED_WARNING);
            return OUTCOME_NO_ELEMENT_SELECTED;
        }
        selection.clear();
        /**
         * I don't care about the returned value so it's ignored.
         */
        try {
            dao.remove(elementsToRemove);
            messages.info(BundleKeys.SELECTED_ELEMENTES_REMOVED_SUCCESSFULLY);
            return OUTCOME_SUCCESS;
        } catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
                final ConstraintViolationException violationException = (ConstraintViolationException) e.getCause();
//                TODO this message should be only for admin
                messages.error(violationException.getMessage());
            }
            return OUTCOME_FAILURE;
        }
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private ListViewHelper()
    {
    }
}
