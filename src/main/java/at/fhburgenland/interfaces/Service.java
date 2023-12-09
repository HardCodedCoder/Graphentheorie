package at.fhburgenland.interfaces;

/**
 * Contract which needs to be implemented by a service.
 */
public interface Service {

    /**
     * Implements the main loop of the service.
     */
    void runService();

    /**
     * Exits the service.
     */
    void exit();
}
