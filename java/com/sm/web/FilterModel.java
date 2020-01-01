package com.sm.web;

import java.util.ArrayList;
import java.util.List;

public class FilterModel {
    interface Filter {
        public void execute(String request);
    }
    static class AuthenticationFilter implements Filter {
        public void execute(String request){
            System.out.println("Authenticating request: " + request);
        }
    }
    static class DebugFilter implements Filter {
        public void execute(String request){
            System.out.println("request log: " + request);
        }
    }
    static class Target {
        public void execute(String request){
            System.out.println("Executing request: " + request);
        }
    }
    static class FilterChain {
        private List<Filter> filters = new ArrayList<Filter>();
        private Target target;

        public void addFilter(Filter filter){
            filters.add(filter);
        }

        public void execute(String request){
            for (Filter filter : filters) {
                filter.execute(request);
            }
            target.execute(request);
        }

        public void setTarget(Target target){
            this.target = target;
        }
    }
    static class FilterManager {
        FilterChain filterChain;

        public FilterManager(Target target){
            filterChain = new FilterChain();
            filterChain.setTarget(target);
        }
        public void setFilter(Filter filter){
            filterChain.addFilter(filter);
        }

        public void filterRequest(String request){
            filterChain.execute(request);
        }
    }
    static class Client {
        FilterManager filterManager;

        public void setFilterManager(FilterManager filterManager){
            this.filterManager = filterManager;
        }

        public void sendRequest(String request){
            filterManager.filterRequest(request);
        }
    }
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }
}
