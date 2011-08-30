# compojure.route.clojurescript

Provides a route that serves compiled ClojureScript.

## Usage

If your ClojureScript is in `cljs/src/` and your index.html refers to
`/cljs/bootstrap.js`

    (defroutes app
      ...
      (compiled-clojurescript "/cljs/"))

ClojureScript will be recompiled as needed.  By default, ClojureScript is
compiled in dev mode, ie without optimizations.  For more advanced usage, see
ring.middleware.clojurescript.

## License

Copyright (C) 2011 Jim Blomo

Distributed under the Eclipse Public License, the same as Clojure.
