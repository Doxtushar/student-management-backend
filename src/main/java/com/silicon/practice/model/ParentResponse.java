package com.silicon.practice.model;

import java.util.List;

public class ParentResponse {

    private List<SchoolDetails> details;

    private List<SectionDetails> sectionDetails;

    private List<TransportDetails> transportDetails;

    public List<SchoolDetails> getDetails() {
        return details;
    }

    public void setDetails(List<SchoolDetails> details) {
        this.details = details;
    }

    public List<SectionDetails> getSectionDetails() {
        return sectionDetails;
    }

    public void setSectionDetails(List<SectionDetails> sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

    public List<TransportDetails> getTransportDetails() {
        return transportDetails;
    }

    public void setTransportDetails(List<TransportDetails> transportDetails) {
        this.transportDetails = transportDetails;
    }
}