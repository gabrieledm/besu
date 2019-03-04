/*
 * Copyright 2019 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.ethereum.eth.sync.worldstate;

import tech.pegasys.pantheon.ethereum.core.Hash;
import tech.pegasys.pantheon.ethereum.worldstate.WorldStateStorage;
import tech.pegasys.pantheon.ethereum.worldstate.WorldStateStorage.Updater;
import tech.pegasys.pantheon.util.bytes.BytesValue;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CodeNodeDataRequest extends NodeDataRequest {

  CodeNodeDataRequest(final Hash hash) {
    super(RequestType.CODE, hash);
  }

  @Override
  protected void doPersist(final Updater updater) {
    updater.putCode(getHash(), getData());
  }

  @Override
  public List<NodeDataRequest> getChildRequests() {
    // Code nodes have nothing further to download
    return Collections.emptyList();
  }

  @Override
  public Optional<BytesValue> getExistingData(final WorldStateStorage worldStateStorage) {
    return worldStateStorage.getCode(getHash());
  }
}
